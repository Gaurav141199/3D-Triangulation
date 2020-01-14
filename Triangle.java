
public class Triangle implements TriangleInterface {
	int count =0;
	public Point P1;
	public Point P2;
	public Point P3;
	Edge edge1 = new Edge(P1,P2);
	Edge edge2 = new Edge(P2,P3);
	Edge edge3 = new Edge(P3,P1);
	Triangle[] neighborlist;
	public boolean visited=false;
	public PointInterface[] triangle = new PointInterface[3];
	public Triangle(Point P1,Point P2,Point P3) {
		this.P1=P1;
		this.P2=P2;
		this.P3=P3;
		triangle[0]=P1;
		triangle[1]=P2;
		triangle[2]=P3;
	}
	public PointInterface[] triangle_coord() {
		return triangle;
	}
	public boolean equals(Triangle T) {
		Point P11 = T.P1;
		Point P21 = T.P2;
		Point P31 = T.P3;
		if((P11.equals(P1) && P21.equals(P2) && P31.equals(P3))||(P11.equals(P1) && P21.equals(P3) && P31.equals(P2)) || (P11.equals(P2) && P21.equals(P1) && P31.equals(P3)) || (P11.equals(P2) && P21.equals(P3) && P31.equals(P1)) || 
				(P11.equals(P3) && P21.equals(P1) && P31.equals(P2)) || (P11.equals(P3) && P21.equals(P2) && P31.equals(P1)))
			return true;
		return false;
	}
	
	public String toString() {
		String line = P1.toString()+P2.toString()+P3.toString();
		return line;
		
	}
}
