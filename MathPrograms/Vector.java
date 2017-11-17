import java.text.DecimalFormat;

/**
 * 
 * 
 * @author Summer Mims
 */

public class Vector
{
	private double x;
	private double y;
	private double z;
	private int set2D = 0;
	private DecimalFormat df = new DecimalFormat("0.000");

	/**
	 * Constructs a new Vector object. (Insert any further description that is
	 * needed)
	 * 
	 * @param x
	 * @param y
	 */
	public Vector(double x, double y)
	{
		setX(x);
		setY(y);
		setZ(0);
		set2D = -1;
	}

	/**
	 * Constructs a new Vector object. (Insert any further description that is
	 * needed)
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(double x, double y, double z)
	{
		setX(x);
		setY(y);
		setZ(z);
	}

	/**
	 * @return the x
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(double x)
	{
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(double y)
	{
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public double getZ()
	{
		return z;
	}

	/**
	 * @param z
	 *            the z to set
	 */
	public void setZ(double z)
	{
		this.z = z;
	}

	public int getSet2D()
	{
		return set2D;
	}

	/**
	 * 
	 * get magnitude of a vector
	 *
	 * @return magnitude
	 */
	public double getMagnitude()
	{
		return Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * 
	 * get direction of a vector
	 *
	 * @return direction in radians
	 */
	public double getDirection()
	{
		return Math.atan(y / x);
	}

	/**
	 * 
	 * get direction in degrees
	 *
	 * @param deg
	 * @return direction in degrees
	 */
	public double getDirection(int deg)
	{
		return Math.atan(y / x) * 180 / Math.PI;
	}

	/**
	 * 
	 * get direction with respect to X axis
	 *
	 * @return
	 */
	public double getDirection3Dx()
	{
		return Math.atan2(Math.sqrt(y * y + z * z), x);
	}

	/**
	 * 
	 * get direction with respect to Y axis
	 *
	 * @return
	 */
	public double getDirection3Dy()
	{
		return Math.atan2(Math.sqrt(x * x + z * z), y);
	}

	/**
	 * 
	 * get direction with respect to Z axis
	 *
	 * @return
	 */
	public double getDirection3Dz()
	{
		return Math.atan2(Math.sqrt(y * y + x * x), z);
	}

	/**
	 * 
	 * normalize a vector
	 *
	 */
	public void normalize()
	{
		double temp = this.getMagnitude();
		setX(x / temp);

		setY(y / temp);

		setZ(z / temp);
	}

	/**
	 * 
	 * get inner product of this and v
	 *
	 * @param v
	 * @return
	 */
	public double innerProduct(Vector v)
	{
		return this.getX() * v.getX() + this.getY() * v.getY()
				+ this.getZ() * v.getZ();
	}

	/**
	 * 
	 * compute the cross product of this and v 
	 *
	 * @param v
	 * @return
	 */
	public Vector crossProduct(Vector v)
	{
		if (this.getSet2D() == v.getSet2D())
		{
			double xN = this.getY() * v.getZ() - this.getZ() * v.getY();
			double yN = this.getZ() * v.getX() - this.getX() * v.getZ();
			double zN = this.getX() * v.getY() - this.getY() * v.getX();
			return new Vector(xN, yN, zN);
		}
		return null;
	}

	/**
	 * 
	 * get angle between this and v
	 *
	 * @param v
	 * @return
	 */
	public double getAngle(Vector v)
	{
		return Math.acos(
				this.innerProduct(v) / this.getMagnitude() / v.getMagnitude());
	}

	public boolean orthogonal(Vector v)
	{
		if ((int) this.innerProduct(v) == 0)
		{
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{

		String s1 = "Vector [" + df.format(this.getX()) + ", "
				+ df.format(this.getY());
		if (set2D != -1)
		{
			s1 += ", " + df.format(this.getZ());
		}
		s1 += "]";
		return s1;
	}

}
