package canal;

public class Canal
{
	private double[] probabilidadPriori;
	private double[][] canal;
	private double[] probabilidadSalida;
	private double[][] probabilidadPosteriori;
	private double[] entropiaPosteriori;

	public Canal(double[] probabilidadesAPriori, double[][] canal)
	{
		this.probabilidadPriori = probabilidadesAPriori;
		this.canal = canal;
		this.calculaProbabilidadSalida();
		this.calculaProbabilidadPosteriori();
		this.calculaEntropiaPosteriori();
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
				this.probabilidadPosteriori[i][j] = this.canal[i][j] * this.probabilidadPriori[i]
						/ this.probabilidadSalida[j];
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

	public double getEquivocacion()
	{
		double equivocacion = 0;
		int n = this.canal[0].length;
		for (int j = 0; j < n; j++)
		{
			equivocacion += this.probabilidadSalida[j] * this.entropiaPosteriori[j];
		}

		return equivocacion;
	}

	public double getInformacionMutua()
	{
		return this.getEntropiaPriori() - this.getEquivocacion();
	}

	public boolean informacionMutuaIsSimetrica()
	{
		return this.getEquivocacion() == this.getEntropiaPriori()
				- this.getInformacionMutua();
	}

	public boolean informacionMutuaIsMayorIgualCero()
	{
		return this.getInformacionMutua() >= 0;
	}

}
