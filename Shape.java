
public class Shape implements ShapeInterface{
	ArrayList1<Point> points;
	ArrayList1<Edge> edges;
	ArrayList1<Triangle> triangles;
	ArrayList1<ArrayList1<Triangle>> connected= new ArrayList1<ArrayList1<Triangle>>();
	int global =1;
	public Shape() {
		points = new ArrayList1<Point>();
		edges = new ArrayList1<Edge>();
		triangles= new ArrayList1<Triangle>();
	}
	
	public float[] tans(float[] triangle_coord) {
		float[] l1=new float[3];
		float[] l2=new float[3];
		float[] m1=new float[3];
		float[] m2=new float[3];
		float[] n1=new float[3];
		float[] n2=new float[3];
		float[] t2=new float[3];
		float[] t=new float[3];
		float[] cos2=new float[3];
		float[] sin2=new float[3];
		float[] tan2=new float[3];
		 l1[1]=triangle_coord[0]-triangle_coord[3];
		 l2[1]=triangle_coord[0]-triangle_coord[6];
		 m1[1]=triangle_coord[1]-triangle_coord[4];
		m2[1]=triangle_coord[1]-triangle_coord[7];
		 n1[1]=triangle_coord[2]-triangle_coord[5];
		 n2[1]=triangle_coord[2]-triangle_coord[8];
		 
		 l1[2]=triangle_coord[3]-triangle_coord[0];
		 l2[2]=triangle_coord[3]-triangle_coord[6];
		 m1[2]=triangle_coord[4]-triangle_coord[1];
		m2[2]=triangle_coord[4]-triangle_coord[7];
		 n1[2]=triangle_coord[5]-triangle_coord[2];
		 n2[2]=triangle_coord[5]-triangle_coord[8];
		 
		l1[0]=triangle_coord[0]-triangle_coord[6];
		l2[0]=triangle_coord[3]-triangle_coord[6];
		 m1[0]=triangle_coord[4]-triangle_coord[7];
		m2[0]=triangle_coord[1]-triangle_coord[7];
		n1[0]=triangle_coord[2]-triangle_coord[8];
		n2[0]=triangle_coord[5]-triangle_coord[8];
		t2[0]=(l1[0]*l2[0]+m1[0]*m2[0]+n1[0]*n2[0])*(l1[0]*l2[0]+m1[0]*m2[0]+n1[0]*n2[0]);
		t[0]=((l1[0]*l1[0]+m1[0]*m1[0]+n1[0]*n1[0])*(l2[0]*l2[0]+m2[0]*m2[0]+n2[0]*n2[0]));
		cos2[0]=t2[0]/t[0];
		sin2[0]=1-cos2[0];
		tan2[0]=sin2[0]/cos2[0];
		
		t2[1]=(l1[1]*l2[1]+m1[1]*m2[1]+n1[1]*n2[1])*(l1[1]*l2[1]+m1[1]*m2[1]+n1[1]*n2[1]);
		t[1]=((l1[1]*l1[1]+m1[1]*m1[1]+n1[1]*n1[1])*(l2[1]*l2[1]+m2[1]*m2[1]+n2[1]*n2[1]));
		cos2[1]=t2[1]/t[1];
		sin2[1]=1-cos2[1];
		tan2[1]=sin2[1]/cos2[1];
		
		t2[2]=(l2[2]*l2[2]+m2[2]*m2[2]+n2[2]*n2[2])*(l2[2]*l2[2]+m2[2]*m2[2]+n2[2]*n2[2]);
		t[2]=((l2[2]*l2[2]+m2[2]*m2[2]+n2[2]*n2[2])*(l2[2]*l2[2]+m2[2]*m2[2]+n2[2]*n2[2]));
		cos2[2]=t2[2]/t[2];
		sin2[2]=2-cos2[2];
		tan2[2]=sin2[2]/cos2[2];
		return tan2;
	}
	
	
	
	public int partition(Edge[] a,int left,int right){
		Edge temp;
		int i=left+1;
		int pos=left;
		int j=right;
		Edge x=a[left];

//INV:a[left...j-1]<=x,a[j+1...i]>x,left+1<=j<=i-1,left<=right

		while(i<=j){ 
				Edge e=a[pos];
				Edge u1=a[i];
				Edge u2=a[j];
				if(u1.distance()<x.distance()){
					temp=a[i]; 
					a[i]=a[pos];	
					a[pos]=temp;
					pos=i;
					i=i+1;
					}
				else{
					temp=a[i]; 
					a[i]=a[j];
					a[j]=temp;
					j=j-1;
					}
				}
		a[j]=x;
		return pos;
		}

    public void quicksort(Edge[] a,int left,int right){
    	if(left<right){
    		int p=partition(a,left,right);
    		quicksort(a,left,p-1);
    		quicksort(a,p+1,right);
    		}
    	}
	
	public boolean addpoint(Point P) {
		for(int i=0;i<points.size;i++) {
			if(points.getObject(i).equals(P)) {
				P = points.getObject(i);
				return false;
			}
		}
		return true;
	}
	
	public boolean addedge(Edge edge) {
		for(int i=0;i<edges.size;i++) {
			if(edge.equals(edges.getObject(i))) {
				edge = edges.getObject(i);
				return false;
			}
		}
		return true;
	}
	
	public boolean collinear(Point P1,Point P2,Point P3) {
		float x1= P1.x;
		float y1= P1.y;
		float z1= P1.z;
		float x2= P2.x;
		float y2= P2.y;
		float z2= P2.z;
		float x3= P3.x;
		float y3= P3.y;
		float z3= P3.z;
		float[] hi = new float[9];
		hi[0]=x1;
		hi[1]=y1;
		hi[2]=z1;
		hi[3]=x2;
		hi[4]=y2;
		hi[5]=z2;
		hi[6]=x3;
		hi[7]=y3;
		hi[8]=z3;
		float[] tan = tans(hi);
		
		if(tan[0]<0.000001 || tan[1]<0.000001 || tan[2]<0.000001)
			return true;
		else
			return false;
	}
	
	public boolean checkneighbour(Triangle T1, Triangle T2) {
//		System.out.println("hi1");
		if((T1.edge1.equals(T2.edge1) || T1.edge1.equals(T2.edge2) || T1.edge1.equals(T2.edge3) ||
				T1.edge2.equals(T2.edge1) || T1.edge2.equals(T2.edge2) || T1.edge2.equals(T2.edge3)
				|| T1.edge3.equals(T2.edge1) || T1.edge3.equals(T2.edge2) ||T1.edge3.equals(T2.edge3)) && (!(T1.equals(T2)))){
			return true;
		}
		return false;
	}
	
	public ArrayList1<Triangle> merge(ArrayList1<Triangle> triangle1,ArrayList1<Triangle> triangle2) {
		ArrayList1<Triangle> list = new ArrayList1<Triangle>();
		list = triangle1;
		for(int i=0;i<triangle2.size;i++) {
			if(!(list.search(triangle2.getObject(i)))){
				list.add(triangle2.getObject(i));
			}
		}
		return list;
	}
	
	public boolean ADD_TRIANGLE(float [] triangle_coord){
//		System.out.println("i");
		float x1= triangle_coord[0];
		float y1= triangle_coord[1];
		float z1= triangle_coord[2];
		float x2= triangle_coord[3];
		float y2= triangle_coord[4];
		float z2= triangle_coord[5];
		float x3= triangle_coord[6];
		float y3= triangle_coord[7];
		float z3= triangle_coord[8];
		Point point1 = new Point(x1,y1,z1);
		Point point2 = new Point(x2,y2,z2);
		Point point3 = new Point(x3,y3,z3);
		if(collinear(point1,point2,point3)==false) {
			
			if(points.size==0) {
				points.add(point1);
				points.add(point2);
				points.add(point3);
			}
			else {
			int y=0;
			for(int i=0;i<points.size;i++) {
				if(point1.equals(points.getObject(i))) {
					point1=points.getObject(i);
					break;
				}
				y++;
				if(y==points.size) {
					points.add(point1);
					break;
				}
				}
			int v=0;
			for(int i=0;i<points.size;i++) {
				if(point2.equals(points.getObject(i))) {
					point2=points.getObject(i);
					break;
				}
				v++;
				if(v==points.size) {
					points.add(point2);
					break;
				}
				}
			int q=0;
			for(int i=0;i<points.size;i++) {
				if(point3.equals(points.getObject(i))) {
					point3=points.getObject(i);
					break;
				}
				q++;
				if(q==points.size) {
					points.add(point3);
					break;
				}
				}
			}
			
//			System.out.println(points.size);
			Edge edge1= new Edge(point1,point2);
			Edge edge2= new Edge(point2,point3);
			Edge edge3= new Edge(point3,point1);
			if(edges.size==0) {
				edges.add(edge1);
				edges.add(edge2);
				edges.add(edge3);
				point1.edgelist.add(edge1);
				point1.edgelist.add(edge3);
				point2.edgelist.add(edge2);
				point2.edgelist.add(edge1);
				point3.edgelist.add(edge2);
				point3.edgelist.add(edge3);
			}
			else {
			int k=0;			
			for(int i=0;i<edges.size;i++) {
				if(edge1.equals(edges.getObject(i))) {
					edge1=edges.getObject(i);
					if(!(point1.edgelist.search(edge1))){
						point1.edgelist.add(edge1);
					}
					if(!(point2.edgelist.search(edge1))){
						point2.edgelist.add(edge1);
					}
					break;
				}
				k++;	
				if(k==edges.size) {
					edges.add(edge1);
					if(!(point1.edgelist.search(edge1))){
						point1.edgelist.add(edge1);
					}
					if(!(point2.edgelist.search(edge1))){
						point2.edgelist.add(edge1);
					}
					break;
				}
			}

			int l=0;
			for(int i=0;i<edges.size;i++) {
				if(edge2.equals(edges.getObject(i))) {
					edge2=edges.getObject(i);
					if(!(point2.edgelist.search(edge2))){
						point2.edgelist.add(edge2);
					}
					if(!(point3.edgelist.search(edge2))){
						point3.edgelist.add(edge2);
					}
					break;
				}
				l++;	
				if(l==edges.size) {
					edges.add(edge2);
					if(!(point2.edgelist.search(edge2))){
						point2.edgelist.add(edge2);
					}
					if(!(point3.edgelist.search(edge2))){
						point3.edgelist.add(edge2);
					}
					break;
				}
			}
			int u=0;
			for(int i=0;i<edges.size;i++) {
				if(edge3.equals(edges.getObject(i))) {
					edge3=edges.getObject(i);
					if(!(point1.edgelist.search(edge3))){
						point1.edgelist.add(edge3);
					}
					if(!(point3.edgelist.search(edge3))){
						point3.edgelist.add(edge3);
					}
					break;
				}
				u++;	
				if(u==edges.size) {
					edges.add(edge3);
					if(!(point1.edgelist.search(edge3))){
						point1.edgelist.add(edge3);
					}
					if(!(point3.edgelist.search(edge3))){
						point3.edgelist.add(edge3);
					}
					break;
				}
			}
			}
//			for(int i=0;i<edges.size;i++) {
//				System.out.println(edges.getObject(i).toString());
//			}
			
			Triangle triangle = new Triangle(point1,point2,point3);
			triangle.edge1=edge1;
			triangle.edge2=edge2;
			triangle.edge3=edge3;
			triangle.count=global;
			global++;
			triangles.add(triangle);
			point1.trianglelist.add(triangle);
//			System.out.println("hi");
//			for(int i=0;i<point1.trianglelist.size;i++) {
//				System.out.println(point1.trianglelist.getObject(i).toString());
//			}
			point2.trianglelist.add(triangle);
			point3.trianglelist.add(triangle);
			edge1.trianglelist.add(triangle);
//			for(int i=0;i<edge1.trianglelist.size;i++) {
//				System.out.println(edge1.trianglelist.getObject(i).toString());
//			}
			edge2.trianglelist.add(triangle);
			edge3.trianglelist.add(triangle);
//			for(int i=0;i<edge3.trianglelist.size;i++) {
//				System.out.println(edge3.trianglelist.getObject(i).toString());
//			}
//			for(int i=0;i<edges.size;i++) {
//				if(edge1.equals(edges.getObject(i))) {
//					edge1=edges.getObject(i);
//					edge1.trianglelist.add(triangle);
//				}
//			}
//			edge1.trianglelist.add(triangle);
//			System.out.println(edge1.trianglelist.size);
//			for(int i=0;i<edges.size;i++) {
//				if(edge2.equals(edges.getObject(i))) {
//					edge2=edges.getObject(i);
//					edge2.trianglelist.add(triangle);
//				}
//			}
//			edge2.trianglelist.add(triangle);
//			for(int i=0;i<edges.size;i++) {
//				if(edge3.equals(edges.getObject(i))) {
//					edge3=edges.getObject(i);
//					edge3.trianglelist.add(triangle);
//				}
//			}
//			edge3.trianglelist.add(triangle);
//			System.out.println("ki");
			ArrayList1<ArrayList1<Triangle>> hey = new ArrayList1<ArrayList1<Triangle>>();
			ArrayList1<Integer> hello = new ArrayList1<Integer>();
			ArrayList1<Triangle> refer = new ArrayList1<Triangle>();
			if(connected.size==0) {
				refer.add(triangle);
				connected.add(refer);
			}
			else {
//				System.out.println(connected.size);
				int g=connected.size;
			for(int i=0;i<g;i++) {
				for(int j=0;j<connected.getObject(i).size;j++) {
					if(checkneighbour(triangle,connected.getObject(i).getObject(j))) {
						connected.getObject(i).add(triangle);
//						System.out.println("hello");
						hello.add(i);
						hey.add(connected.getObject(i));
						break;
					}
				}
			}
			if(hey.size==0) {
				refer.add(triangle);
				connected.add(refer);
			}
//			System.out.println("hi");
			if(hey.size!=0) {
			for(int i=0;i<hey.size;i++) {
				refer=merge(hey.getObject(0),hey.getObject(i));
//				for(int o =0;o<refer.size;o++) {
//					System.out.println(refer.getObject(o).toString());
//					
//				}
//				System.out.println("again");
			}
			for(int i=hello.size-1;i>0;i--) {

//				System.out.println("yo");
				connected.remove(hello.getObject(i));
			}
			connected.update(hello.getObject(0), refer);

//			System.out.println(connected.getObject(0).getObject(1));
			}
			}
			return true;
			
			
		}			
		return false;
		}
	//checked
	public int TYPE_MESH(){
		boolean b=false;
		for(int i=0;i<edges.size;i++) {
			if(edges.getObject(i).trianglelist.size>=3) {
				return 3;
			}
			if(edges.getObject(i).trianglelist.size==1) {
				b=true;
			}
		}
	if(b==true)
		return 2;
	
		return 1;
	}
	//checked
	public EdgeInterface [] BOUNDARY_EDGES(){
		ArrayList1<Edge> edges1= new ArrayList1<Edge>();
		for(int i=0;i<edges.size;i++) {
			if(edges.getObject(i).trianglelist.size==1) {
				edges1.add(edges.getObject(i));
			}
		}
		Edge[] edges = new Edge[edges1.size];
		for(int i=0;i<edges1.size;i++) {
			edges[i]=edges1.getObject(i);
		}
		quicksort(edges,0,edges1.size-1);
//		System.out.println(edges.length);
//		for(int i=0;i<edges.length;i++) {
//			System.out.println(edges[i].toString());
//		}
		int n = edges.length;
//		System.out.println(point.length);
		for(int p=0;p<n-1;p++) {
			for(int l=0;l<n-p-1;l++) {
				if (edges[l].compareTo(edges[l+1])==1)
                {
                    // swap temp and arr[i]
                    Edge temp = edges[l];
                    edges[l] = edges[l+1];
                    edges[l+1] = temp;
                }
			}
		}
		if(edges.length==0)
			return null;
		return edges;
	}
	//checked
	public int COUNT_CONNECTED_COMPONENTS(){
		
		return connected.size;
	}
	
	
	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){

		float x1= triangle_coord[0];
		float y1= triangle_coord[1];
		float z1= triangle_coord[2];
		float x2= triangle_coord[3];
		float y2= triangle_coord[4];
		float z2= triangle_coord[5];
		float x3= triangle_coord[6];
		float y3= triangle_coord[7];
		float z3= triangle_coord[8];
		Point point1 = new Point(x1,y1,z1);
		Point point2 = new Point(x2,y2,z2);
		Point point3 = new Point(x3,y3,z3);
		Edge edge1= new Edge(point1,point2);
		Edge edge2= new Edge(point2,point3);
		Edge edge3= new Edge(point3,point1);
		Triangle triangle = new Triangle(point1,point2,point3);
//		System.out.println("hi");
		for(int k=0;k<triangles.size;k++) {
			if(triangles.getObject(k).equals(triangle)) {
				triangle = triangles.getObject(k);
				ArrayList1<Triangle> intriangle= new ArrayList1<Triangle>();
//				for(int i=0;i<triangles.size;i++) {
//					if(triangle.equals(triangles.getObject(i))) {
//						if(triangles.getObject(i).edge1.trianglelist.size>1) {
//							for(int j=0;j<triangles.getObject(i).edge1.trianglelist.size;j++) {
//								if(!(triangles.getObject(i).edge1.trianglelist.getObject(j).equals(triangle))) {
//									intriangle.add(triangle);
//								}
//							}
//						}
//						if(triangles.getObject(i).edge2.trianglelist.size>1) {
//							for(int j=0;j<triangles.getObject(i).edge2.trianglelist.size;j++) {
//								if(!(triangles.getObject(i).edge2.trianglelist.getObject(j).equals(triangle))) {
//									intriangle.add(triangle);
//								}
//							}
//						}
//						if(triangles.getObject(i).edge3.trianglelist.size>1) {
//							for(int j=0;j<triangles.getObject(i).edge3.trianglelist.size;j++) {
//								if(!(triangles.getObject(i).edge3.trianglelist.getObject(j).equals(triangle))) {
//									intriangle.add(triangle);
//								}
//							}
//						}
//					}
//				}

//				System.out.println("hi1");
				for(int i= 0;i<edges.size;i++) {
					if(edge1.equals(edges.getObject(i))) {
						if(edges.getObject(i).trianglelist.size>1) {
							for(int j=0;j<edges.getObject(i).trianglelist.size;j++) {
								if(!(edges.getObject(i).trianglelist.getObject(j).equals(triangle))) {
									for(int m=0;m<edges.getObject(i).trianglelist.size;m++) {
//										System.out.println(edges.getObject(i).trianglelist.getObject(m).toString());
									}
									intriangle.add(edges.getObject(i).trianglelist.getObject(j));
								}
							}
						}
					}
					if(edge2.equals(edges.getObject(i))) {
						if(edges.getObject(i).trianglelist.size>1) {
							for(int j=0;j<edges.getObject(i).trianglelist.size;j++) {
								if(!(edges.getObject(i).trianglelist.getObject(j).equals(triangle))) {
									for(int m=0;m<edges.getObject(i).trianglelist.size;m++) {
//										System.out.println(edges.getObject(i).trianglelist.getObject(m).toString());
									}
									intriangle.add(edges.getObject(i).trianglelist.getObject(j));
								}
							}
						}
					}
					if(edge3.equals(edges.getObject(i))) {
						if(edges.getObject(i).trianglelist.size>1) {
							for(int j=0;j<edges.getObject(i).trianglelist.size;j++) {
								if(!(edges.getObject(i).trianglelist.getObject(j).equals(triangle))) {
//									System.out.println("hi");
									intriangle.add(edges.getObject(i).trianglelist.getObject(j));
								}
							}
						}
					}
				}
					Triangle[] arr = new Triangle[intriangle.size];
					for(int h=0;h<intriangle.size;h++) {
						arr[h]=intriangle.getObject(h);
					}
					int n = arr.length;
					for(int p=0;p<n-1;p++) {
						for(int l=0;l<n-p-1;l++) {
							if (arr[l].count > arr[l+1].count)
			                {
			                    // swap temp and arr[i]
			                    Triangle temp = arr[l];
			                    arr[l] = arr[l+1];
			                    arr[l+1] = temp;
			                }
						}
					}
//					System.out.println(intriangle.size);
//					for(int i=0;i<arr.length;i++) {
//						String line = arr[i].toString();
//						System.out.println(line);
//					}
				return arr;
			}
		}
		return null;
	}
	//checked
	


	public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		float x1= triangle_coord[0];
		float y1= triangle_coord[1];
		float z1= triangle_coord[2];
		float x2= triangle_coord[3];
		float y2= triangle_coord[4];
		float z2= triangle_coord[5];
		float x3= triangle_coord[6];
		float y3= triangle_coord[7];
		float z3= triangle_coord[8];
		Point point1 = new Point(x1,y1,z1);
		Point point2 = new Point(x2,y2,z2);
		Point point3 = new Point(x3,y3,z3);
		Edge edge1= new Edge(point1,point2);
		Edge edge2= new Edge(point2,point3);
		Edge edge3= new Edge(point3,point1);
		Triangle triangle = new Triangle(point1 , point2,point3);
		for(int i=0;i<triangles.size;i++) {
			if(triangle.equals(triangles.getObject(i))) {
				Edge[] arr = new Edge[3];
				arr[0]=edge1;
				arr[1]=edge2;
				arr[2]=edge3;


//				for(int l=0;l<arr.length;l++) {
//				String line = arr[l].toString();
//				System.out.println(line);
//			}
				return arr;
			}
		}
		return null;
	}
	//checked
	public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		float x1= triangle_coord[0];
		float y1= triangle_coord[1];
		float z1= triangle_coord[2];
		float x2= triangle_coord[3];
		float y2= triangle_coord[4];
		float z2= triangle_coord[5];
		float x3= triangle_coord[6];
		float y3= triangle_coord[7];
		float z3= triangle_coord[8];
		Point point1 = new Point(x1,y1,z1);
		Point point2 = new Point(x2,y2,z2);
		Point point3 = new Point(x3,y3,z3);
		Triangle triangle = new Triangle(point1 , point2,point3);
		for(int i=0;i<triangles.size;i++) {
			if(triangle.equals(triangles.getObject(i))) {
				Point[] arr = new Point[3];
				arr[0]=point1;
				arr[1]=point2;
				arr[2]=point3;
//				for(int l=0;l<arr.length;l++) {
//					String line = arr[l].toString();
//					System.out.println(line);
//				}
				return arr;
			}
		}
		return null;
	}
	//checked
	public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){
//		System.out.println("ji");
		float x1= triangle_coord[0];
		float y1= triangle_coord[1];
		float z1= triangle_coord[2];
		float x2= triangle_coord[3];
		float y2= triangle_coord[4];
		float z2= triangle_coord[5];
		float x3= triangle_coord[6];
		float y3= triangle_coord[7];
		float z3= triangle_coord[8];
		Point point1 = new Point(x1,y1,z1);
		Point point2 = new Point(x2,y2,z2);
		Point point3 = new Point(x3,y3,z3);
		Triangle triangle = new Triangle(point1,point2,point3);
		ArrayList1<Triangle> intriangle = new ArrayList1<Triangle>();
		int kp=0;
		for(int i=0;i<points.size;i++) {	
			if(point1.equals(points.getObject(i))) {
				point1=points.getObject(i);
				kp++;
				for(int j=0; j<point1.trianglelist.size;j++) {
					if((!(intriangle.search(point1.trianglelist.getObject(j))))&& (!(point1.trianglelist.getObject(j).equals(triangle)))) {
						intriangle.add(point1.trianglelist.getObject(j));
					}
				}
			}
			if(point2.equals(points.getObject(i))) {
				point2=points.getObject(i);
				kp++;
				for(int j=0; j<point2.trianglelist.size;j++) {
					if((!(intriangle.search(point2.trianglelist.getObject(j)))) && (!(point2.trianglelist.getObject(j).equals(triangle)))) {
						intriangle.add(point2.trianglelist.getObject(j));
					}
				}
			}
			if(point3.equals(points.getObject(i))) {
				point3=points.getObject(i);
				kp++;
				for(int j=0; j<point3.trianglelist.size;j++) {
					if((!(intriangle.search(point3.trianglelist.getObject(j)))) && (!(point3.trianglelist.getObject(j).equals(triangle)))) {
						intriangle.add(point3.trianglelist.getObject(j));
					}
				}
			}
			if(kp==3) {
				break;
			}
		}
		Triangle[] arr = new Triangle[intriangle.size];
		for(int h=0;h<intriangle.size;h++) {
			arr[h]=intriangle.getObject(h);
		}
		int n = arr.length;
		for(int k=0;k<n-1;k++) {
			for(int l=0;l<n-k-1;l++) {
				if (arr[l].count > arr[l+1].count)
                {
                    // swap temp and arr[i]
                    Triangle temp = arr[l];
                    arr[l] = arr[l+1];
                    arr[l+1] = temp;
                }
			}
		}
//		System.out.println(arr.length);
//		for(int i=0;i<arr.length;i++) {
//			String line = arr[i].toString();
//			System.out.println(line);
//		}
		if(arr.length==0) {
			return null;
		}
		return arr;
		}
	//checked
	public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){

		float x= point_coordinates[0];
		float y= point_coordinates[1];
		float z= point_coordinates[2];
		Point P= new Point(x,y,z);
		int j=0;
		for(int i=0;i<points.size;i++) {
			if(P.equals(points.getObject(i))) {
				P = points.getObject(i);
				break;
			}
			j++;
			if(j==points.size) {
				return null;
			}
		}
		ArrayList1<Point> inpoint = new ArrayList1<Point>();
		for(int i=0;i<P.edgelist.size;i++) {
			if(P.edgelist.getObject(i).P1.equals(P)){
				inpoint.add(P.edgelist.getObject(i).P2);
			}
			else {
				inpoint.add(P.edgelist.getObject(i).P1);
			}
			
		}
		Point[] pointlist = new Point[inpoint.size];
		for(int i=0;i<inpoint.size;i++) {
			pointlist[i]=inpoint.getObject(i);
		}
//		System.out.println(pointlist.length);
//		for(int o=0;o<pointlist.length;o++) {
//			System.out.println(pointlist[o].toString());
//		}
		return pointlist;
		}
	//checked
	public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
		float x= point_coordinates[0];
		float y= point_coordinates[1];
		float z= point_coordinates[2];
		Point P= new Point(x,y,z);
		int j=0;
//		System.out.println("test1");
		for(int i=0;i<points.size;i++) {
//			System.out.println("test2");
			if(P.equals(points.getObject(i))) {
				P = points.getObject(i);
				break;
			}
			j++;
			if(j==points.size) {
				return null;
			}
		}
		ArrayList1<Triangle> inpoint = new ArrayList1<Triangle>();
		for(int i=0;i<P.trianglelist.size;i++) {
//			System.out.println("test3");
			inpoint.add(P.trianglelist.getObject(i));
		}
		Triangle[] pointlist = new Triangle[inpoint.size];
		for(int i=0;i<inpoint.size;i++) {
			pointlist[i]=inpoint.getObject(i);
		}
		int n = pointlist.length;
		for(int k=0;k<n-1;k++) {
			for(int l=0;l<n-k-1;l++) {
				if (pointlist[l].count > pointlist[l+1].count)
                {
                    // swap temp and arr[i]
                    Triangle temp = pointlist[l];
                    pointlist[l] = pointlist[l+1];
                    pointlist[l+1] = temp;
                }
			}
		}
//		for(int o=0;o<pointlist.length;o++) {
//			System.out.println(pointlist[o].toString());
//		}
		return pointlist;
		}
	//checked
	public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
		float x= point_coordinates[0];
		float y= point_coordinates[1];
		float z= point_coordinates[2];
		Point P= new Point(x,y,z);
		int j=0;
		for(int i=0;i<points.size;i++) {
			if(P.equals(points.getObject(i))) {
				P = points.getObject(i);
				break;
			}
			j++;
			if(j==points.size) {
				return null;
			}
		}
		
		Edge[] pointlist = new Edge[P.edgelist.size];
		for(int i=0;i<P.edgelist.size;i++) {
			pointlist[i]=P.edgelist.getObject(i);
		}
//		for(int o=0;o<pointlist.length;o++) {
//			System.out.println(pointlist[o].toString());
//		}
		return pointlist;
		}
	//checked
	public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ 
		float x= point_coordinates[0];
		float y= point_coordinates[1];
		float z= point_coordinates[2];
		Point P= new Point(x,y,z);
		int j=0;
		for(int i=0;i<points.size;i++) {
			if(P.equals(points.getObject(i))) {
				P = points.getObject(i);
				break;
			}
			j++;
			if(j==points.size) {
				return null;
			}
		}
		ArrayList1<Triangle> inpoint = new ArrayList1<Triangle>();
		for(int i=0;i<P.trianglelist.size;i++) {
			inpoint.add(P.trianglelist.getObject(i));
		}
		Triangle[] pointlist = new Triangle[inpoint.size];
		for(int i=0;i<inpoint.size;i++) {
			pointlist[i]=inpoint.getObject(i);
		}
		int n = pointlist.length;
		for(int k=0;k<n-1;k++) {
			for(int l=0;l<n-k-1;l++) {
				if (pointlist[l].count > pointlist[l+1].count)
                {
                    // swap temp and arr[i]
                    Triangle temp = pointlist[l];
                    pointlist[l] = pointlist[l+1];
                    pointlist[l+1] = temp;
                }
			}
		}
//		for(int o=0;o<pointlist.length;o++) {
//			System.out.println(pointlist[o].toString());
//		}
		return pointlist;
		}
	//checked
	public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){ 

		float x1= edge_coordinates[0];
		float y1= edge_coordinates[1];
		float z1= edge_coordinates[2];
		float x2= edge_coordinates[3];
		float y2= edge_coordinates[4];
		float z2= edge_coordinates[5];
		Point point1 = new Point(x1,y1,z1);
		Point point2 = new Point(x2,y2,z2);
		Edge edge1= new Edge(point1,point2);
		int h=0;
		for(int i =0;i<edges.size;i++) {
			if(edges.getObject(i).equals(edge1)) {
				edge1=edges.getObject(i);
				break;
			}
			h++;
			if(h==edges.size) {
				return null;
			}
		}
		Triangle[] intriangle = new Triangle[edge1.trianglelist.size];
		for(int y=0;y<edge1.trianglelist.size;y++) {
			intriangle[y]= edge1.trianglelist.getObject(y);
		}
//		int n = intriangle.length;
//		for(int k=0;k<n-1;k++) {
//			for(int l=0;l<n-k-1;l++) {
//				if (intriangle[l].count > intriangle[l+1].count)
//                {
//                    // swap temp and arr[i]
//                    Triangle temp = intriangle[l];
//                    intriangle[l] = intriangle[l+1];
//                    intriangle[l+1] = temp;
//                }
//			}
//		}
//		for(int i=0;i<intriangle.length;i++) {
//			System.out.println(intriangle[i].toString());
//		}
		return intriangle;
		}
	//checked

	public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
		float xa1= triangle_coord_1[0];
		float ya1= triangle_coord_1[1];
		float za1= triangle_coord_1[2];
		float xa2= triangle_coord_1[3];
		float ya2= triangle_coord_1[4];
		float za2= triangle_coord_1[5];
		float xa3= triangle_coord_1[6];
		float ya3= triangle_coord_1[7];
		float za3= triangle_coord_1[8];
		Point pointa1 = new Point(xa1,ya1,za1);
		Point pointa2 = new Point(xa2,ya2,za2);
		Point pointa3 = new Point(xa3,ya3,za3);
		Triangle trianglea = new Triangle(pointa1 , pointa2,pointa3);
		float xb1= triangle_coord_2[0];
		float yb1= triangle_coord_2[1];
		float zb1= triangle_coord_2[2];
		float xb2= triangle_coord_2[3];
		float yb2= triangle_coord_2[4];
		float zb2= triangle_coord_2[5];
		float xb3= triangle_coord_2[6];
		float yb3= triangle_coord_2[7];
		float zb3= triangle_coord_2[8];
		Point pointb1 = new Point(xb1,yb1,zb1);
		Point pointb2 = new Point(xb2,yb2,zb2);
		Point pointb3 = new Point(xb3,yb3,zb3);
		Triangle triangleb = new Triangle(pointb1 , pointb2,pointb3);
//		for(int i=0;i<connected.getObject(0).size;i++) {
//			System.out.println(connected.getObject(0).getObject(i).toString());
//		}
//		if((connected.getObject(0)).search(trianglea)) {
//			System.out.println("lo");
//		}
//		if(connected.getObject(0).getObject(0).equals(trianglea)) {
//			System.out.println("hi");
//		}
		for(int i=0;i<connected.size;i++) {
			int u=0;
			for(int j=0;j<connected.getObject(i).size;j++) {
				if(connected.getObject(i).getObject(j).equals(trianglea) || connected.getObject(i).getObject(j).equals(triangleb)) {
//					System.out.println("hi");
					u++;
				}
				if(u==2) {
					return true;
				}
			}
			
				
		}
		return false;
		}
	
	public boolean containspoint(ArrayList1<Triangle> list, Point point) {
		for(int i=0;i<list.size;i++) {
			if(list.getObject(i).P1.equals(point)) {
				return true;
			}
			if(list.getObject(i).P2.equals(point)) {
				return true;
			}
			if(list.getObject(i).P3.equals(point)) {
				return true;
			}
		}
		return false;
	}
	
	public Point centroid(ArrayList1<Triangle> list) {
		ArrayList1<Point> pointslist = new ArrayList1<Point>();
		for(int i=0;i<list.size;i++) {
			if(!(pointslist.search(list.getObject(i).P1))){
				pointslist.add(list.getObject(i).P1);
			}
			if(!(pointslist.search(list.getObject(i).P2))){
				pointslist.add(list.getObject(i).P2);
			}
			if(!(pointslist.search(list.getObject(i).P3))){
				pointslist.add(list.getObject(i).P3);
			}
		}
		float x=0;
		float y=0;
		float z=0;
		for(int i=0;i<pointslist.size;i++) {
			x=pointslist.getObject(i).getX()+x;
			y=pointslist.getObject(i).getY()+y;
			z=pointslist.getObject(i).getZ()+z;
		}
		int k=pointslist.size;
		x=x/k;
		y=y/k;
		z=z/k;
		Point P = new Point(x,y,z);
		return P;
	}
	
	public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
		float x = point_coordinates[0];
		float y = point_coordinates[1];
		float z = point_coordinates[2];
		Point point = new Point(x,y,z);
		for(int i=0;i<points.size;i++) {
			if(points.getObject(i).equals(point)) {
				point=points.getObject(i);
			}
		}
		for(int i=0;i<connected.size;i++) {
			if(containspoint(connected.getObject(i),point)) {
				point.centroids.add(centroid(connected.getObject(i)));
			}
		}
		if(point.centroids.size==0) {
			return null;
		}
		return point.centroids.getObject(0);
		}
	
	public PointInterface [] CENTROID (){

		ArrayList1<Point> pointlist = new ArrayList1<Point>();
		for(int i=0;i<connected.size;i++) {
			Point P1 =connected.getObject(i).getObject(0).P1;
			float x1 = P1.getX();
			float y1 = P1.getY();
			float z1 = P1.getZ();
			float[] arr1 = new float[3];
			arr1[0]=x1;
			arr1[1]=y1;
			arr1[2]=z1;
			for(int p=0;p<points.size;p++) {
				if(P1.equals(points.getObject(p))) {
					P1.centroids=new ArrayList1<Point>();
					break;
				}
			}
			Point add1 = (Point) CENTROID_OF_COMPONENT(arr1);
			Point P2 =connected.getObject(i).getObject(0).P2;
			float x2 = P2.getX();
			float y2 = P2.getY();
			float z2 = P2.getZ();
			float[] arr2 = new float[3];
			arr2[0]=x2;
			arr2[1]=y2;
			arr2[2]=z2;
			for(int p=0;p<points.size;p++) {
				if(P2.equals(points.getObject(p))) {
					P2.centroids=new ArrayList1<Point>();
					break;
				}
			}
			Point add2 = (Point) CENTROID_OF_COMPONENT(arr2);
			Point P3 =connected.getObject(i).getObject(0).P3;
			float x3 = P3.getX();
			float y3 = P3.getY();
			float z3 = P3.getZ();
			float[] arr3 = new float[3];
			arr3[0]=x3;
			arr3[1]=y3;
			arr3[2]=z3;
			for(int p=0;p<points.size;p++) {
				if(P3.equals(points.getObject(p))) {
					P3.centroids=new ArrayList1<Point>();
					break;
				}
			}
			Point add3 = (Point) CENTROID_OF_COMPONENT(arr3);
			int yer = 0;
			for(int j=0;j<P1.centroids.size;j++) {
				for(int k=0;k<P2.centroids.size;k++) {
					for(int l=0;l<P3.centroids.size;l++) {
						if(yer!=0) break;
						if(P1.centroids.getObject(j).equals(P2.centroids.getObject(k)) && P1.centroids.getObject(j).equals(P3.centroids.getObject(l))) {
							int y=0;
							for(int o= 0 ;o<pointlist.size;o++) {
								if( pointlist.getObject(o).equals(P1.centroids.getObject(j))) {
									y++;
									
									break;
								}
								
							}
							if(y==0) {
								pointlist.add(P1.centroids.getObject(j));
								yer++;
							}
						}
					}
				}
			}
		}
		Point[] point = new Point[pointlist.size];
		for(int i=0;i<pointlist.size;i++) {
			point[i]= pointlist.getObject(i);
		}
//		System.out.println(point.length);
//		for (int h=0;h<point.length;h++) {
//			System.out.println(point[h].toString());
//		}
		int n = point.length;
//		System.out.println(point.length);
		for(int p=0;p<n-1;p++) {
			for(int l=0;l<n-p-1;l++) {
				if (point[l].compareTo(point[l+1])==1)
                {
                    // swap temp and arr[i]
                    Point temp = point[l];
                    point[l] = point[l+1];
                    point[l+1] = temp;
                }
			}
		}
		return point;
	}
	
	public float distance(Point point1,Point point2) {
		float xa=point1.getX();
		float ya=point1.getY();
		float za=point1.getZ();
		float xb=point2.getX();
		float yb=point2.getY();
		float zb=point2.getZ();
		float z=((xa-xb)*(xa-xb))+((ya-yb)*(ya-yb))+((za-zb)*(za-zb));
		return z;
	}

	public Pair mindistance(ArrayList1<Triangle>list1,ArrayList1<Triangle>list2) {
		if(list1.size!=0 && list2.size!=0) {
		ArrayList1<Point> pointlist1= new ArrayList1<Point>();
		for(int i=0;i<list1.size;i++) {
			if(!(pointlist1.search(list1.getObject(i).P1))) {
				pointlist1.add(list1.getObject(i).P1);
			}
			if(!(pointlist1.search(list1.getObject(i).P2))) {
				pointlist1.add(list1.getObject(i).P2);
			}
			if(!(pointlist1.search(list1.getObject(i).P3))) {
				pointlist1.add(list1.getObject(i).P3);
			}
		}
		ArrayList1<Point> pointlist2= new ArrayList1<Point>();
		for(int i=0;i<list2.size;i++) {
			if(!(pointlist2.search(list2.getObject(i).P1))) {
				pointlist2.add(list2.getObject(i).P1);
			}
			if(!(pointlist2.search(list2.getObject(i).P2))) {
				pointlist2.add(list2.getObject(i).P2);
			}
			if(!(pointlist2.search(list2.getObject(i).P3))) {
				pointlist2.add(list2.getObject(i).P3);
			}
		}
//		for(int i=0;i<pointlist1.size;i++) {
//			System.out.println(pointlist1.getObject(i).toString());
//		}
		Pair pair= new Pair(pointlist1.getObject(0),pointlist2.getObject(0));
		float x=distance(pointlist1.getObject(0),pointlist2.getObject(0));
		for(int i=0;i<pointlist1.size;i++) {
			for(int j=0;j<pointlist2.size;j++) {
				float z = distance(pointlist1.getObject(i),pointlist2.getObject(j));
				if(z<x) {
					x=z;
					pair= new Pair(pointlist1.getObject(i),pointlist2.getObject(j));
					pair.distance=x;
				}
			}
		}
//		System.out.println(pair.point1.toString());
//		System.out.println(pair.point2.toString());
		return pair;}
		else return null;
		
	}
	
	public PointInterface [] CLOSEST_COMPONENTS(){
//		System.out.println("hi");
		if(connected.size>1) {
		Pair pair = mindistance(connected.getObject(0),connected.getObject(1));
		float x = pair.distance;
		for(int i=0;i<connected.size;i++) {
			for(int j=0;j<connected.size;j++) {
				if(i!=j && i<j) {
					if(mindistance(connected.getObject(i),connected.getObject(j)).distance<x) {
						pair=mindistance(connected.getObject(i),connected.getObject(j));
					}
				}
			}
		}
		Point[] arr = new Point[2];
		arr[0]=pair.point1;
		arr[1]=pair.point2;
//		System.out.println(arr[0]);
//		System.out.println(arr[1]);
		return arr;
		}
//		System.out.println("yo");
		return null;
	}
	
	public void neighborgen(ArrayList1<Triangle>list) {
		for(int i=0;i<list.size;i++) {
			Triangle triangle =list.getObject(i);
			float x1= triangle.P1.getX();
			float y1= triangle.P1.getY();
			float z1= triangle.P1.getZ();
			float x2= triangle.P2.getX();
			float y2= triangle.P2.getY();
			float z2= triangle.P2.getZ();
			float x3= triangle.P3.getX();
			float y3= triangle.P3.getY();
			float z3= triangle.P3.getZ();
			float[] arr = new float[9];
			arr[0]=x1;
			arr[1]=y1;
			arr[2]=z1;
			arr[3]=x2;
			arr[4]=y2;
			arr[5]=z2;
			arr[6]=x3;
			arr[7]=y3;
			arr[8]=z3;
			triangle.neighborlist=(Triangle[]) NEIGHBORS_OF_TRIANGLE(arr);
//			if(triangle.neighborlist==null)System.out.println("hi");
//			else System.out.println("hello");
		}
	}
	
	public int maxdia(ArrayList1<Triangle>list) {
		int maxdiam = 0;
		for(int j=0;j<list.size;j++) {
			
			Triangle triangle= list.getObject(j);
			int dia = -1;
			ArrayList1<Triangle>array1= new ArrayList1<Triangle>();
			
			array1.add(triangle);
			triangle.visited=true;
			while(array1.size > 0) {
				dia=dia+1;System.out.println(dia);
				
				ArrayList1<Triangle>array= new ArrayList1<Triangle>();
				for(int k=0; k <array1.size; k ++) {
					System.out.println("h1");
					Triangle triangle1 = array1.getObject(k);
					for(int i=0;i<triangle1.neighborlist.length;i++) {
						System.out.println("h2");
						if(triangle1.neighborlist[i].visited==false) {
							System.out.println("h3");
							array.add(triangle1.neighborlist[i]);
							triangle1.neighborlist[i].visited=true;
						}
					}
					
				}
				array1=array;
			}
			System.out.println("h4");
			if(maxdiam < dia)maxdiam = dia;
			
			for(int i=0; i <list.size; i ++) {
				list.getObject(i).visited=false;
			}	
		}
		return maxdiam;
	}
	
	
	public int MAXIMUM_DIAMETER(){
		if(connected.size>0) {
		int y=connected.getObject(0).size;
		int z=0;
		for(int i=0;i<connected.size;i++) {
			if(y<connected.getObject(i).size) {
				y=connected.getObject(i).size;
				z=i;
			}
		}
		ArrayList1<Triangle> trianglelist = connected.getObject(z);
		neighborgen(trianglelist);
		return maxdia(trianglelist);
		}
		else 
			return -1;
		
		}
}

