INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (26,null,1,'RECEITA BRUTA DE VENDAS (+)','#ababab','E','S');
INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (27,null,2,'DEDUCOES (-)','#fddddd','S','S');
INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (28,null,3,'CUSTO MERCADORIA VENDIDA (-)','#bababa','S','S');
INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (29,null,4,'DESPESAS VARIAVEIS VENDA (-)','#a3a3a3','S','S');
INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (30,null,5,'CUSTOS FIXOS VENDA (-)','#ababab','S','S');
INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (31,null,6,'DESPESAS FIXAS OPERACIONAIS (-)','#a6a6a6','S','S');
INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (32,null,7,'DESPESAS-RECEITAS (+-)','#bfbfbf','X','S');
INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (33,null,8,'IMOBILIZADOS-INVESTIMENTOS','#d6f3ff','S','S');
INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (34,null,10,'RETIRADAS','#c7e4ff','S','S');
INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (35,null,9,'MOVIMENTOS NAO OPERACIONAIS','#ffc370','X','S');
INSERT INTO `cd_plcon_conta` (`id`,`cdpessoaemp_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (36,null,1,'RECEITA',NULL,'E','S');

INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (33,26,1,'RECEITA DE VENDAS',NULL,'E','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (34,27,1,'IMPOSTOS SOBRE VENDAS',NULL,'S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (35,27,2,'DEVOLUCOES',NULL,'S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (36,28,1,'CUSTOS DIRETOS PRODUTOS E SERVICOS',NULL,'S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (37,29,1,'DESPESAS COMERCIAIS E FINANCEIRAS',NULL,'S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (38,30,1,'CUSTOS INDIRETOS PRODUTOS E SERVICOS',NULL,'S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (39,31,1,'DESPESAS ADMINISTRATIVAS',NULL,'S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (40,31,2,'OUTRAS ENTRADAS','#ffffff','E','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (41,31,3,'OUTRAS SAIDAS','#ffffff','S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (42,32,1,'DESPESAS FINANCEIRAS','#ffffff','S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (43,32,2,'RECEITAS FINANCEIRAS','#ffffff','E','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (44,33,1,'IMOBILIZADOS',NULL,'S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (45,33,2,'INVESTIMENTOS',NULL,'E','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (46,34,1,'DIVIDENDOS-LUCROS',NULL,'S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (47,35,1,'OUTRAS ENTRADAS',NULL,'E','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (48,35,2,'OUTRAS SAIDAS',NULL,'S','S');
INSERT INTO `cd_plcon_macro` (`id`,`cdplconconta_id`,`masc`,`nome`,`cor`,`tipo`,`exibe`) VALUES (49,36,1,'VENDA PRODUTOS',NULL,'E','S');

INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (14,33,1,'VENDA PGTO DINHEIRO',NULL,NULL,'E','S','1.1.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (15,33,2,'VENDA PAGTO BOLETO',NULL,NULL,'E','S','1.1.2','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (16,34,1,'IMPOSTOS DE ICMS, IPI',NULL,NULL,'S','S','2.1.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (18,35,1,'DEVOLUCAO DE CLIENTES',NULL,'','S','S','2.2.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (19,36,1,'FORNECEDOR DIRETO',NULL,NULL,'S','S','3.1.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (20,36,2,'E COMMERCE',NULL,NULL,'S','S','3.1.2','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (21,37,1,'COMISSAO VENDA',NULL,NULL,'S','S','4.1.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (22,37,2,'TARIFAS CARTOES E BOLETOS',NULL,NULL,'S','S','4.1.2','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (23,38,1,'FORNECEDORES INDIRETO',NULL,NULL,'S','S','5.1.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (24,38,2,'CUSTOS COMPRA FRETE',NULL,NULL,'S','S','5.1.2','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (25,39,1,'AGUA',NULL,NULL,'S','S','6.1.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (26,39,2,'ENERGIA',NULL,NULL,'S','S','6.1.2','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (27,39,3,'INTERNET',NULL,NULL,'S','S','6.1.3','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (28,40,1,'EMPRESTIMOS PEGOS',NULL,NULL,'E','S','6.2.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (29,41,1,'PAGAMENTO DE EMPRESTIMOS',NULL,NULL,'S','S','6.3.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (30,42,1,'JUROS PAGAMENTOS',NULL,NULL,'S','S','7.1.1','02');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (31,43,1,'RENDIMENTOS APLICACAO',NULL,NULL,'E','S','7.2.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (32,44,1,'MAQUINAS E EQUIPAMENTOS',NULL,NULL,'S','S','8.1.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (33,44,2,'MOVEIS',NULL,NULL,'S','S','8.1.2','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (34,45,1,'TERRENOS',NULL,NULL,'E','S','8.2.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (35,45,2,'IMOVEIS',NULL,NULL,'E','S','8.2.2','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (36,46,1,'DIVIDENDOS EMPREGADOS',NULL,NULL,'S','S','10.1.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (37,46,2,'DIVIDENDOS SOCIOS',NULL,NULL,'S','S','10.1.2','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (38,47,1,'EMPRESTIMOS PEGOS',NULL,NULL,'E','S','9.1.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (39,48,1,'EMPRESTIMOS PAGOS',NULL,NULL,'S','S','9.2.1','00');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (40,43,2,'JUROS DE RECEBIMENTOS',NULL,NULL,'E','S','7.2.2','01');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (42,47,2,'ENTRADA DE TROCO',NULL,NULL,'E','S','9.1.2','03');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (43,48,2,'SAIDA DE TROCO',NULL,NULL,'S','S','9.2.2','04');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (44,41,2,'TAXA DE CARTAO DE CREDITO OU DEBITO',NULL,NULL,'S','S','6.3.2','05');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (45,41,3,'TAXA DE BOLETOS',NULL,NULL,'S','S','6.3.3','06');
INSERT INTO `cd_plcon_micro` (`id`,`cdplconmacro_id`,`masc`,`nome`,`cor`,`explica`,`tipo`,`exibe`,`mascfinal`,`tipolocal`) VALUES (46,49,1,'VENDA BALCAO',NULL,NULL,'E','S','1.1.1','00');

