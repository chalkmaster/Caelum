package com.fortsystems.caelum.angelus;

import java.util.Random;

import com.fortsystems.caelum.mundi.Mundi;

public class Corpo {
	public Mundi mundo;
	public int energia = 100;
	public int fadiga = 0;
	public int fadigaMaxima = 100;
	public int velocidade = 10;
	public int forcaFisica = 25;
	public int equilibrio = 10;
	public eEstadoProntidao estadoProntidao = eEstadoProntidao.Normal;
	public ePosicaoCorpo posicaoCorpo = ePosicaoCorpo.Pe;
	public ParteCorpo cabeca;
	public ParteCorpo tronco;
	public ParteCorpo bracoDireito;
	public ParteCorpo bracoEsquerdo;
	public ParteCorpo pernaDireita;
	public ParteCorpo pernaEsquerda;
	
	public void Nascer()
	{
		this.cabeca = new ParteCorpo(eTipoParteCorpo.cabeca, 0);
		this.tronco = new ParteCorpo(eTipoParteCorpo.tronco, 10);
		this.bracoDireito = new ParteCorpo(eTipoParteCorpo.bracoDireito, 32);
		this.bracoEsquerdo = new ParteCorpo(eTipoParteCorpo.bracoEsquerdo, 30);
		this.pernaDireita = new ParteCorpo(eTipoParteCorpo.pernaDireita, 42);
		this.pernaEsquerda = new ParteCorpo(eTipoParteCorpo.pernaEsquerda, 38);
	}
	
	public void ReceberDano(Movimento movimento){
		int defesa;
		int escolha = new Random().nextInt(1);
		ParteCorpo parteCorpo;
		
		if (this.estadoProntidao == eEstadoProntidao.Defesa){
			switch(movimento.direcao){
				case Cima:
					if (this.posicaoCorpo == ePosicaoCorpo.Agachado || this.posicaoCorpo == ePosicaoCorpo.Deitado)
						return;
				case Centro:
					if (this.posicaoCorpo == ePosicaoCorpo.Deitado)
						return;
					parteCorpo = escolha == 0 ? this.bracoDireito : this.bracoEsquerdo;
					break;	
				case Baixo:
					parteCorpo = escolha == 0 ? this.pernaDireita : this.pernaEsquerda;
					break;	
				case Direita:
					parteCorpo = this.bracoDireito;
					break;
				case Esquerda:
					parteCorpo = this.bracoEsquerdo;
					break;
				default:
					return;
			}
		}
		else {
			switch(movimento.direcao){
			case Cima:
				if (this.posicaoCorpo == ePosicaoCorpo.Agachado || this.posicaoCorpo == ePosicaoCorpo.Deitado)
					return;
				parteCorpo = this.cabeca;
			case Centro:	
				if (this.posicaoCorpo == ePosicaoCorpo.Deitado)
					return;
				parteCorpo = this.tronco;
				break;	
			case Baixo:
				parteCorpo = escolha == 0 ? this.pernaDireita : this.pernaEsquerda;
				break;	
			case Direita:
				parteCorpo = this.bracoDireito;
				break;
			case Esquerda:
				parteCorpo = this.bracoEsquerdo;
				break;
			default:
				return;
			}

		}
		
		defesa = parteCorpo.resistencia + (int)(this.forcaFisica * 0.25);
		int dano = movimento.intencidade - defesa;
		if (dano > 0){
			energia -= dano;
			forcaFisica -= (int)(dano * 0.01);
			fadiga += (int)(dano * 0.1);			
			parteCorpo.inutilizado = (dano * 4.25) > parteCorpo.resistencia;			
			
			if((parteCorpo == this.cabeca || parteCorpo == this.tronco) && parteCorpo.inutilizado)
				this.Desmaiar();
			
			if (parteCorpo == this.cabeca && (dano * 1.75) > parteCorpo.resistencia)
			{
				this.Cair();
				this.equilibrio *= 0.7;
			}
			if (parteCorpo == this.tronco && (dano * 3.25) > parteCorpo.resistencia)
			{
				this.Cair();
				this.forcaFisica *= 0.7;
			}
		}
		
	}
	
	private void Desmaiar() {
		this.energia = 0;
		this.Cair();		
	}

	public void Cair(){
		this.estadoProntidao = eEstadoProntidao.Normal;
		this.posicaoCorpo = ePosicaoCorpo.Deitado;
		this.mundo.Interagir(this);
	}
	
	public void Levantar(){
		this.posicaoCorpo = ePosicaoCorpo.Pe;
		this.mundo.Interagir(this);
	}
	
	public void Agachar(){
		this.posicaoCorpo = ePosicaoCorpo.Agachado;
		this.mundo.Interagir(this);
	}
	
	public void Falar(String fala) {
		this.mundo.Interagir(this, fala);
		
	}

	public void DeferirMovimento(Golpe golpe) {
		for(int i = 0; i < golpe.movimento.size(); i++)
			this.DeferirMovimento(golpe.movimento.get(i), (i < golpe.movimento.size() - 1));
	}
	
	public void DeferirMovimento(Movimento movimento) {
		this.DeferirMovimento(movimento, false);
	}
	
	public void DeferirMovimento(Movimento movimento, boolean composto) {
		if (movimento.intencidade > this.forcaFisica)
			movimento.intencidade = this.forcaFisica;
		
		int duracaoMovimento = this.velocidade * (1 - (this.fadiga / this.fadigaMaxima));
		switch (movimento.direcao){
			case Cima:
				duracaoMovimento /= 2;
				break;
			case Baixo:
				duracaoMovimento *= 2;
				break;
			case Direita:
			case Esquerda:
			case Tras:
				duracaoMovimento *= 0.75;
				break;
			default:
				break;
		}
		
		movimento.duracao = duracaoMovimento;
		this.fadiga += movimento.intencidade;
		
		this.mundo.Interagir(this, movimento, composto);		
	}

}
