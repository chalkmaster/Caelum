package com.fortsystems.caelum.angelus;

public class ParteCorpo {
	public int resistencia;
	public boolean inutilizado = false;
	public eTipoParteCorpo tipoParteCorpo;
	
	public ParteCorpo(eTipoParteCorpo parteCorpo)
	{
		this(parteCorpo, 10);
	}

	public ParteCorpo(eTipoParteCorpo parteCorpo, int resistencia) {
		this.tipoParteCorpo = parteCorpo;
		this.resistencia = resistencia;
	}
}
