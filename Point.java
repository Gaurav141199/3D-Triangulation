
public class Point implements PointInterface {
	public float x;
	public float y;
	public float z;
	public float position[]= new float[3];
	public ArrayList1<Triangle> trianglelist=new ArrayList1<Triangle>();
	public ArrayList1<Edge> edgelist=new ArrayList1<Edge>();
	public ArrayList1<Point> centroids = new ArrayList1<Point>();
	public Point(float x,float y,float z) {
		this.x=x;
		this.y=y;
		this.z=z;
		position[0]=x;
		position[1]=y;
		position[2]=z;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public float getZ() {
		return z;
	}
	public boolean equals(Point P) {
		float x1 = P.getX();
		float y1 = P.getY();
		float z1 = P.getZ();
		if(x1==x & y1==y & z1==z) {
			return true;
		}
		return false;
	}

	//[x,y,z]  3 dimensions first is x second y and third is z.
	// This order should be followed

	public float [] getXYZcoordinate() {		
		return position;
	}
	public String toString() {
		String Line = String.valueOf(x)+" "+String.valueOf(y)+" "+String.valueOf(z)+" ";
		return Line;
	}
	
	public int compareTo(Point P) {
		if(x>P.x) return 1;
		else if(x<P.x)return -1;
		else if(y>P.y) return 1;
		else if(y<P.y)return -1;
		else if(z>P.z) return 1;
		else if(z<P.z)return -1;
		return 0;
	}
}
