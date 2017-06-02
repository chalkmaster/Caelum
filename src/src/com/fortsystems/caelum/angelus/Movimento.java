package com.fortsystems.caelum.angelus;

public class Movimento {
	public Movimento(eTipoMovimento m, eDirecao d, int i) {
		tipoMovimento = m;
		direcao = d;
		intencidade = i;
	}
	public eTipoMovimento tipoMovimento;
	public eDirecao direcao;
	public int intencidade;
	public int duracao = 10;
	public int equilibrio = 1;
}
