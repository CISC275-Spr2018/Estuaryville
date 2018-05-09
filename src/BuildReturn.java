import java.io.Serializable;

public class BuildReturn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4047089187268782226L;
	BuildError be;
	Active a;
	public BuildReturn(BuildError be, Active a) {
		this.be = be;
		this.a = a;
	}
	public BuildError getBuildError() {
		return be;
	}
	public Active getActive() {
		return a;
	}
}
