package br.com.cid.model;

import javax.persistence.Embeddable;

@Embeddable
public enum UF {

	ACRE("AC"),
	ALAGOAS("AL"),
	AMAPA("AP"),
	AMAZONAS("AM"),
	BAHIA("BA"),
	CEARA("CE"),
	DISTRITO_FEDERAL("DF"),
	ESPIRITO_SANTO("ES"),
	GOIAS("GO"),
	MARANHAO("MA"),
	MATA_GROSSO("MT"),
	MATO_GROSSO_SUL("MS"),
	MINAS_GERAIS("MG"),
	PARA("PA"),
	PARAIBA("PB"),
	PARANA("PR"),
	PERNABUCO("PE"),
	PIAUI("PI"),
	RIO_JANEIRO("RJ"),
	RIO_GRANDE_NORTE("RN"),
	RIO_GRANDE_SUL("RS"),
	RONDONIA("RO"),
	RORAIMA("RR"),
	SANTA_CATARINA("SC"),
	SAO_PAULO("SP"),
	SERGIPE("SE"),
	TOCANTINS("TO");
	
	private String sigla;

	UF(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}
