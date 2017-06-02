package com.fortsystems.caelum.angelus;

import java.util.Dictionary;

public class Mente {
	private Dictionary<eTipoSom, Dictionary<eTipoDecisao, boolean[]>> _bcAudicao;
	private Dictionary<eTipoIntuicao, Dictionary<eTipoDecisao, boolean[]>> _bcIntuicao;
	private Dictionary<eTipoObjeto, Dictionary<eTipoDecisao, boolean[]>> _bcVisao;
	private Dictionary<eTipoCheiro, Dictionary<eTipoDecisao, boolean[]>> _bcOlfato;
	private Dictionary<eTipoGosto, Dictionary<eTipoDecisao, boolean[]>> _bcPaladar;
	private Dictionary<eTipoToque, Dictionary<eTipoDecisao, boolean[]>> _bcTato;
	  
	public Decisao InterpretarPercepcao(Percepcao percepcao) {
		switch (percepcao.TipoEstimulo){
			case Audicao:
				return this.InterpretarSons(percepcao.mundo.Sons);
				break;
			case Intuicao:
				break;
			case Visao:
				return this.InterpretarImagensVistas(percepcao.mundo.ObjetosVisiveis);
				break;
			case Olfato:
				return this.InterpretarCheiros(percepcao.mundo.Odores);
				break;
			case Paladar:
				break;
			case Tato:
				return this.InterpretarToque(percepcao.mundo.ObjetosEncostandoEmMim);
				break;
		}
		
		return null;
	}

	public String EncontrarRespostaPara(Percepcao percepcao) {
		// TODO Auto-generated method stub
		return null;
	}

	public Golpe EncontrarMelhorGolpePara(Percepcao percepcao) {
		// TODO Auto-generated method stub
		return null;
	}

	public void AvaliarResultadoAcao(Decisao decisao) {
		// TODO Auto-generated method stub
		
	}

}
