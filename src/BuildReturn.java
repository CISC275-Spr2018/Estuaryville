
public class BuildReturn {
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
