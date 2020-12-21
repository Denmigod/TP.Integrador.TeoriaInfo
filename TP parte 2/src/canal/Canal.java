package canal;

import utilidades.Matrices;

public class Canal
{
	private double[] probabilidadPriori;
	private double[][] canal;
	private double[] probabilidadSalida;
	private double[][] probabilidadPosteriori;
	private double[] entropiaPosteriori;
	private double[][] probabilidadesSimultaneas;

	public Canal(double[] probabilidadesAPriori, double[][] canal)
	{
		this.probabilidadPriori = probabilidadesAPriori;
		this.canal = canal;
		this.calculaProbabilidadSalida();
		this.calculaProbabilidadPosteriori();
		this.calculaEntropiaPosteriori();
		this.calculaProbabilidadesSimultaneas();
	}

	/**
	 * @return the probabilidadPriori
	 */
	public double[] getProbabilidadPriori()
	{
		return probabilidadPriori;
	}

	/**
	 * @param probabilidadesSimultaneas the probabilidadesSimultaneas to set
	 */
	public void setProbabilidadesSimultaneas(double[][] probabilidadesSimultaneas)
	{
		this.probabilidadesSimultaneas = probabilidadesSimultaneas;
	}

	/**
	 * @return the probabilidadSalida
	 */
	public double[] getProbabilidadSalida()
	{
		return probabilidadSalida;
	}

	/**
	 * @return the probabilidadPosteriori
	 */
	public double[][] getProbabilidadPosteriori()
	{
		return probabilidadPosteriori;
	}

	/**
	 * @return the entropiaPosteriori
	 */
	public double[] getEntropiaPosteriori()
	{
		return entropiaPosteriori;
	}

	/**
	 * @return the probabilidadesAPriori
	 */
	public double[] getProbabilidadesAPriori()
	{
		return probabilidadPriori;
	}

	/**
	 * @return the canal
	 */
	public double[][] getCanal()
	{
		return canal;
	}

	private void calculaProbabilidadSalida()
	{
		int m = this.canal.length;
		int n = this.canal[0].length;
		this.probabilidadSalida = new double[n];
		for (int j = 0; j < n; j++)
		{
			this.probabilidadSalida[j] = 0;
			for (int i = 0; i < m; i++)
			{
				this.probabilidadSalida[j] += this.probabilidadPriori[i] * this.canal[i][j];
			}
		}

	}

	private void calculaProbabilidadPosteriori()
	{
		int m = this.canal.length;
		int n = this.canal[0].length;
		this.probabilidadPosteriori = new double[m][n];
		for (int j = 0; j < n; j++)
		{
			for (int i = 0; i < m; i++)
			{
				if (this.probabilidadSalida[j] == 0)
				{
					this.probabilidadPosteriori[i][j] = 0;
				} else
				{
					this.probabilidadPosteriori[i][j] = this.canal[i][j] * this.probabilidadPriori[i]
							/ this.probabilidadSalida[j];
				}

			}
		}

	}

	public void calculaProbabilidadesSimultaneas() // nuevo
	{
		this.probabilidadesSimultaneas = new double[this.probabilidadPriori.length][this.canal[0].length];
		for (int i = 0; i < this.probabilidadPriori.length; i++)
		{
			for (int j = 0; j < this.canal[0].length; j++)
			{
				this.probabilidadesSimultaneas[i][j] = this.probabilidadPriori[i] * this.canal[i][j];
			}
		}
	}

	private void calculaEntropiaPosteriori()
	{
		int m = this.canal.length;
		int n = this.canal[0].length;
		this.entropiaPosteriori = new double[n];
		for (int j = 0; j < n; j++)
		{
			this.entropiaPosteriori[j] = 0;
			for (int i = 0; i < m; i++)
			{
				double cantInfoPosteriori;
				if (this.probabilidadPosteriori[i][j] == 0)
				{
					cantInfoPosteriori = 0;
				} else
				{
					cantInfoPosteriori = (-1) * Math.log(this.probabilidadPosteriori[i][j]) / Math.log(2);
				}

				this.entropiaPosteriori[j] += this.probabilidadPosteriori[i][j] * cantInfoPosteriori;
			}
		}
	}

	public double getEntropiaPriori()
	{
		double entropia = 0;
		int m = this.probabilidadPriori.length;

		for (int i = 0; i < m; i++)
		{

			double cantInfoPriori;
			if (this.probabilidadPriori[i] == 0)
			{
				cantInfoPriori = 0;
			} else
			{
				cantInfoPriori = (-1) * Math.log(this.probabilidadPriori[i]) / Math.log(2);
			}
			entropia += this.probabilidadPriori[i] * cantInfoPriori;
		}

		return entropia;
	}

	public double getEntropiaSalida()
	{
		double entropia = 0;
		int n = this.probabilidadSalida.length;

		for (int j = 0; j < n; j++)
		{
			double cantInfoSalida;
			if (this.probabilidadSalida[j] == 0)
			{
				cantInfoSalida = 0;
			} else
			{
				cantInfoSalida = (-1) * Math.log(this.probabilidadSalida[j]) / Math.log(2);
			}

			entropia += this.probabilidadSalida[j] * cantInfoSalida;
		}

		return entropia;
	}

	public double getEntropiaAfin() // nuevo
	{
		double entropia = 0;
		for (int i = 0; i < this.probabilidadesSimultaneas.length; i++)
		{
			for (int j = 0; j < this.probabilidadesSimultaneas[0].length; j++)
			{
				if (this.probabilidadesSimultaneas[i][j] != 0)
					entropia += this.probabilidadesSimultaneas[i][j]
							* Math.abs(-Math.log(this.probabilidadesSimultaneas[i][j]) / Math.log(2));
			}
		}
		return entropia;
	}

	public double getEquivocacionEntrada()
	{
		double equivocacion = 0;
		int n = this.canal[0].length;
		for (int j = 0; j < n; j++)
		{
			equivocacion += this.probabilidadSalida[j] * this.entropiaPosteriori[j];
		}

		return equivocacion;
	}

	public double getEquivocacionSalida() // nuevo
	{
		double entropia = 0;
		for (int i = 0; i < this.probabilidadesSimultaneas.length; i++)
		{
			for (int j = 0; j < this.probabilidadesSimultaneas[0].length; j++)
			{
				if (this.canal[i][j] != 0)
					entropia += this.probabilidadesSimultaneas[i][j]
							* Math.abs(-Math.log(this.canal[i][j]) / Math.log(2));
			}
		}
		return entropia;
	}

	public double getInformacionMutua()
	{
		return this.getEntropiaPriori() - this.getEquivocacionEntrada();
	}

	public double getInformacionMutuaInversa()
	{
		return this.getEntropiaSalida() - this.getEquivocacionSalida();
	}

	public boolean informacionMutuaIsSimetrica()
	{
		return this.getEquivocacionEntrada() == this.getEntropiaPriori() - this.getInformacionMutua();

	}

	public boolean informacionMutuaIsMayorIgualCero()
	{
		return this.getInformacionMutua() >= 0;
	}

	public String muestraCanal()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Vector de entradas:\n" + Matrices.matrizToString(this.getProbabilidadesAPriori()));
		sb.append("\n");
		sb.append("Matriz del canal:\n" + Matrices.matrizToString(this.getCanal()));
		sb.append("\n");
		sb.append("P(bj):\n" + Matrices.matrizToString(this.probabilidadSalida));
		sb.append("\n");
		sb.append("P(ai/bj):\n" + Matrices.matrizToString(this.probabilidadPosteriori));
		sb.append("\n");
		sb.append("P(a,b):\n" + Matrices.matrizToString(this.probabilidadesSimultaneas));
		sb.append("\n");
		sb.append("H(A/b):\n" + Matrices.matrizToString(this.entropiaPosteriori));
		sb.append("\n");
		sb.append("H(A):\n" + String.format("%.5f", this.getEntropiaPriori()) + "\n");
		sb.append("\n");
		sb.append("H(B):\n" + String.format("%.5f", this.getEntropiaSalida()) + "\n");
		sb.append("\n");
		sb.append("H(A/B):\n" + String.format("%.5f", this.getEquivocacionEntrada()) + "\n");
		sb.append("\n");
		sb.append("H(B/A):\n" + String.format("%.5f", this.getEquivocacionSalida()) + "\n");
		sb.append("\n");
		sb.append("I(A,B):\n" + String.format("%.5f", this.getInformacionMutua()) + "\n");
		sb.append("\n");
		sb.append("I(B,A):\n" + String.format("%.5f", this.getInformacionMutuaInversa()) + "\n");
		sb.append("\n");
		sb.append("H(A,B):\n" + String.format("%.5f", this.getEntropiaAfin()) + "\n");

		return sb.toString();
	}

}
