
public class Edge implements EdgeInterface{
	public Point P1;
	public Point P2;
	public PointInterface[] edge = new PointInterface[2];
	public ArrayList1<Triangle> trianglelist;
	public Edge(Point P1,Point P2) {
		this.P1=P1;
		this.P2=P2;
		edge[0]=P1;
		edge[1]=P2;
		trianglelist= new ArrayList1<Triangle>();
	}
	public PointInterface[] edgeEndPoints() {
		return edge;
	}
	public float distance() {
			float x = P1.x-P2.x;
			float y = P1.y-P2.y;
			float z = P1.z-P2.z;
			float k = x*x + y*y+ z*z;
			return k;
	}

	public boolean equals(Edge edge) {
		Point P11= edge.P1;
		Point P12= edge.P2;
		if( (P11.equals(P1) && P12.equals(P2) ) || ( P11.equals(P2) && P12.equals(P1) ))
			return true;
		
		return false;
	}
	public String toString() {
		String line = P1.toString()+P2.toString();
		return line;
	}
	public int compareTo(Edge edge) {
		if(this.distance()>edge.distance()) {
			return 1;
		}
		else if (this.distance()<edge.distance()) {
			return -1;
		}
		else return 0;
	}
}
