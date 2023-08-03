package com.midas.api.util;

/* Documentação de POOL de conexao do C3P0
### *****************************
### **** Propriedades do C3P0 ***
### *****************************

# Número de conexõs que o pool tentará adiquirir durante a inicialização. Deve
ser um número entre minPoolSize e maxPoolSize.
c3p0.initialPoolSize=5

# Número mínimo de conexões que o pool irá manter.
c3p0.minPoolSize=5

# Número máximo de conexões que o pool irá manter.
c3p0.maxPoolSize=15

# Segundos que uma Conexão será mantida no pool sem ser usada, antes de ser
descartada. Zero significa que a conexão nunca expira.
c3p0.maxIdleTime=60

# O tamanho do cache do C3P0 para PreparedStatements. Se o valor de ambos, maxStatements
e maxStatementsPerConnection, é zero, o cache será desabilitado. Se
maxStatements é zero mas maxStatementsPerConnection é um valor diferente de
zero, o cache será habilitado, mas sem um limite global, apenas com um limite
por conexão. maxStatements controla o número total de Statements dos quais é
feito cache, para todas as conexões. Se setado, deve ser um valor relativamente
alto, já que cada Conexão do pool terá um determinado número de statements
colocado em cache. Como um exemplo, considere quantos PreparedStatements
distintos são frequentemente usados na sua aplicação e multiplique esse
número por maxPoolSize para chegar num valor apropriado. Apesar do parâmetro
maxStatements ser o padrão para o JDBC controlar o cache de statements, usuários
podem achar mais intuitivo o uso do parâmetro maxStatementsPerConnection.
c3p0.maxStatements=0

# O número de PreparedStatements que o c3p0 irá colocar em cache, para cada conexão
do pool. Se ambos maxStatements e maxStatementsPerConnection são zero, o cache
de consultas ficará inativo. Se maxStatementsPerConnection é zero, mas maxStatements
é um valor não nulo, o cache de consultas será habilitado, e um limite global
imposto, mas por outro lado, não existirá nenhum limite individual por conexão.
Se setado, maxStatementsPerConnection deveria ser um valor, aproximado, do número
de PreparedStatements, distintos, que são frequentemente usados na sua aplicação
mais dois ou três, para que as consultas menos comuns não tirem as mais comuns
do cache. Apesar de maxStatements ser o parâmetro padrão em JDBC para controlar
o cache de consultas, o usuário pode achar mais intuitivo usar o parâmetro
maxStatementsPerConnection.
c3p0.maxStatementsPerConnection=10

# Determina quantas conexões por vez o c3p0 tenta adquirir quando o pool não tem
conexões inativas para serem usadas.
c3p0.acquireIncrement=1

# Se idleConnectionTestPeriod é um número maior que zero, c3p0 irá testar todas
as conexões inativas, que estão no pool e não fizeram o check-out, de X em X
segundos, onde X é o valor de idleConnectionTestPeriod.
c3p0.idleConnectionTestPeriod=60

# O número de milisegundos que um cliente chamando getConnection() irá esperar
por uma Conexão, via check-in ou uma nova conexão adquirida quando o pool estiver
esgotado. Zero siginifica esperar indefinidademento. Setar qualquer valor positivo
causará um time-out com uma SQLException depois de passada a quantidade especificada
de milisegundos.
c3p0.checkoutTimeout=5000

# Tempo em milisegundos que o c3p0 irá esperar entre tentivas de aquisição.
c3p0.acquireRetryDelay=1000

# Define quantas vezes o c3p0 tentará adquirir uma nova Conexão da base de dados
antes de desistir. Se esse valor é menor ou igual a zero, c3p0 tentará adquirir
uma nova conexão indefinidamente.
c3p0.acquireRetryAttempts=5

# Se true, um pooled DataSource declarará a si mesmo quebrado e ficará permanentemente
fechado caso não se consiga uma Conexão do banco depois de tentar acquireRetryAttempts
vezes. Se falso, o fracasso para obter uma Conexão jogará uma exceção, porém
o DataSource permanecerá valido, e tentará adquirir novamente, seguindo uma nova
chamada para getConnection().
c3p0.breakAfterAcquireFailure=false

# Número de segundos que conexões acima do limite minPoolSize deverão permanecer
inativas no pool antes de serem fechadas. Destinado para aplicações que desejam
reduzir agressivamente o número de conexões abertas, diminuindo o pool novamente
para minPoolSize, se, seguindo um pico, o nível de load diminui e Conexões não
são mais requeridas. Se maxIdleTime está definido, maxIdleTimeExcessConnections
deverá ser um valor menor para que o parâmetro tenho efeito. Zero significa que
não existirá nenhuma imposição, Conexões em excesso não serão mais fechadas.
c3p0.maxIdleTimeExcessConnections=30

# c3p0 é muito assíncrono. Operações JDBC lentas geralmente são executadas por
helper threads que não detém travas de fechamento. Separar essas operações atravéz
de múltiplas threads pode melhorar significativamente a performace, permitindo
que várias operações sejam executadas ao mesmo tempo.
c3p0.numHelperThreads=3

# Se true, e se unreturnedConnectionTimeout está definido com um valor positivo,
então o pool capturará a stack trace (via uma exceção) de todos os checkouts
de Conexões, e o stack trace será impresso quando o checkout de Conexões der
timeout. Este paramêtro é destinado para debug de aplicações com leak de
Conexões, isto é, aplicações que ocasionalmente falham na liberação/fechamento
de Conexões, ocasionando o crescimento do pool, e eventualmente na sua exaustão
(quando o pool atinge maxPoolSize com todas as suas conexões em uso e perdidas).
Este paramêtro deveria ser setado apenas para debugar a aplicação, já que capturar
o stack trace deixa mais o lento o precesso de check-out de Conexões.
c3p0.debugUnreturnedConnectionStackTraces=false

# Segundos. Se setado, quando uma aplicação realiza o check-out e falha na realização
do check-in [i.e. close()] de um Conexão, dentro de período de tempo especificado,
o pool irá, sem cerimonias, destruir a conexão [i.e. destroy()]. Isto permite
que aplicações com ocasionais leaks de conexão sobrevivam, ao invéz de exaurir
o pool. E Isto é uma pena. Zero significa sem timeout, aplicações deveriam fechar
suas próprias Conexões. Obviamente, se um valor positivo é definido, este valor
deve ser maior que o maior valor que uma conexão deveria permanecer em uso. Caso
contrário, o pool irá ocasionalmente matar conexões ativas, o que é ruim. Isto
basicamente é uma péssima idéia, porém é uma funcionalidade pedida com frequência.
Consertem suas aplicações para que não vazem Conexões!!! Use esta funcionalidade
temporariamente em combinação com debugUnreturnedConnectionStackTraces para
descobrir onde as conexões esão vazando!
c3p0.unreturnedConnectionTimeout=0
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.midas.api.constant.MidasConfig;

public class ConnExtraUtil {
	/* CONSTANTES DE MANIPULACAO DA BASE DE DADOS */
	protected static Connection con = null;
	protected static PreparedStatement pstm = null;
	protected static ResultSet rs = null;

	// CONECTA A BASE
	public Connection getConexao(String banco, MidasConfig mc) {
		Connection conexao = null;
		// DADOS DA CONEXAO
		String driver = mc.Driverclass;
		String url = mc.Url + banco + "?useSSL=false";
		String usuario = mc.Username;
		String senha = mc.Password;
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, usuario, senha);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexao;
	}

	/* METODO PARA FECHAR O PREPARED_STATEMENT */
	public void preparedStatmentClose(PreparedStatement pstm) {
		try {
			if (pstm != null)
				pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* METODO PARA FECHAR A CONEXAO */
	public void connectionClose(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
