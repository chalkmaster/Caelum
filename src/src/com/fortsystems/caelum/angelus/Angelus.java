package com.fortsystems.caelum.angelus;


public class Angelus {
	private Mente _mente;
	private Corpo _corpoFisico;
	
	public void Sentir(Percepcao percepcao)
	{
		Decisao decisao = _mente.InterpretarPercepcao(percepcao);
		Agir(decisao);
		_mente.AvaliarResultadoAcao(decisao);
	}
	
	public void Agir(Decisao decisao)
	{
		switch (decisao.tipoDecisao){
			case Falar:
				_corpoFisico.Falar(_mente.EncontrarRespostaPara(decisao.percepcaoOriginal));
				break;
			case Atacar:
				Golpe golpe = _mente.EncontrarMelhorGolpePara(decisao.percepcaoOriginal);
				_corpoFisico.DeferirMovimento(golpe);
				break;
			case Abaixar:
				_corpoFisico.Agachar();
				break;
			case Defender:
				break;
			case Esquivar:
				break;
			case Levantar:
				_corpoFisico.Levantar();
				break;
			case Pular:
				_corpoFisico.DeferirMovimento(new Movimento(eTipoMovimento.Pular, eDirecao.Cima, 10));
				break;
			case Recuar:
				break;
			case NaoReagir:
				break;
		}
		
	}
	
}
