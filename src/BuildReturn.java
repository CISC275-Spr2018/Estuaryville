import java.io.Serializable;

/**
 * What is returned when building a building. Contains an error and a new active screen.
 * @author Riley
 *
 */
public class BuildReturn implements Serializable{
	private static final long serialVersionUID = -4047089187268782226L;
	BuildError be;
	Active a;
	/**
	 * Creates a new instance of BuildReturn
	 * @param be the building error
	 * @param a the new active screen
	 */
	public BuildReturn(BuildError be, Active a) {
		this.be = be;
		this.a = a;
	}
	/**
	 * Returns the building error
	 * @return the building error
	 */
	public BuildError getBuildError() {
		return be;
	}
	/**
	 * Returns the new active screen
	 * @return the new active screen
	 */
	public Active getActive() {
		return a;
	}
}
