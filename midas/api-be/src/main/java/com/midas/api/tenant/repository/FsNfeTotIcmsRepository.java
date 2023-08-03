package com.midas.api.tenant.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midas.api.tenant.entity.FsNfeTotIcms;

@Repository
public interface FsNfeTotIcmsRepository extends JpaRepository<FsNfeTotIcms, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE FsNfeTotIcms c SET "
			+ "c.vbc = (SELECT SUM(icms.vbc) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vbcst = (SELECT SUM(icms.vbcst) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vcofins = (SELECT SUM(cof.vcofins) FROM FsNfeItemCofins cof WHERE cof.fsnfe_id = ?1), "
			+ "c.vdesc = (SELECT SUM(it.vdesc) FROM FsNfeItem it WHERE it.fsnfe.id = ?1), "
			+ "c.vdescext = (SELECT SUM(it.vdescext) FROM FsNfeItem it WHERE it.fsnfe.id = ?1), "
			+ "c.vfcp = (SELECT SUM(icms.vfcp) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vfcpst = (SELECT SUM(icms.vfcpst) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vfcpstret = (SELECT SUM(icms.vfcpstret) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vfrete = (SELECT SUM(it.vfrete) FROM FsNfeItem it WHERE it.fsnfe.id = ?1), "
			+ "c.vicms = (SELECT SUM(icms.vicms) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vicmsdeson = (SELECT SUM(icms.vicmsdeson) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vipi = (SELECT SUM(ipi.vipi) FROM FsNfeItemIpi ipi WHERE ipi.fsnfe_id = ?1), "
			+ "c.voutro = (SELECT SUM(it.voutro) FROM FsNfeItem it WHERE it.fsnfe.id = ?1), "
			+ "c.vpis = (SELECT SUM(pis.vpis) FROM FsNfeItemPis pis WHERE pis.fsnfe_id = ?1), "
			+ "c.vprod = (SELECT SUM(it.vprod) FROM FsNfeItem it WHERE it.fsnfe.id = ?1), "
			+ "c.vseg = (SELECT SUM(it.vseg) FROM FsNfeItem it WHERE it.fsnfe.id = ?1), "
			+ "c.vst = (SELECT SUM(icms.vicmsst) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vicmsufdest = (SELECT SUM(icms.vicmsdif) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vfcpufdest = (SELECT SUM(icms.vfcpdif) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vicmsufremet = (SELECT SUM(icms.vicmsdifremet) FROM FsNfeItemIcms icms WHERE icms.fsnfe_id = ?1), "
			+ "c.vtottrib = (SELECT SUM(it.vtottrib) FROM FsNfeItem it WHERE it.fsnfe.id = ?1), "
			+ "c.vnf = (SELECT SUM(it.vtot + it.vtottrib + it.vfrete - it.vdescext) FROM FsNfeItem it WHERE it.fsnfe.id = ?1) "
			+ "WHERE c.fsnfe_id = ?1")
	void updateTributos(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE FsNfeTotIcms c SET c.vbc = 0,c.vbcst = 0,c.vcofins = 0,c.vdesc = 0, "
			+ "c.vfcp = 0,c.vfcpst = 0,c.vfcpstret = 0,c.vfrete = 0,c.vicms = 0, "
			+ "c.vicmsdeson = 0,c.vipi = 0,c.voutro = 0,c.vpis = 0,c.vprod = 0, "
			+ "c.vseg = 0,c.vst = 0,c.vtottrib = 0,c.vnf = 0 WHERE c.fsnfe_id = ?1")
	void updateTributosZera(Long id);
}
