
public class ArrayList<E> {
	int size=0;
	public E[] list;
	public ArrayList(){
		list = (E[]) new Object[1];
	}
	public void add(E object) {
		if(size==list.length) {
			int newsize= size*2;
			E[] temp =  (E[]) new Object[newsize];
			for(int i=0;i<size;i++) {
				temp[i]=list[i];
			}
			temp[size]=object;
			list = (E[]) new Object[newsize];
			list = temp;
		}
		else {
			list[size]=object;
		}
		size++;
	}
	
	public E getObject(int i) {
		return list[i];
	}
	public boolean search(E object) {
		for(int i=0;i<size;i++) {
			if(list[i].equals(object)) {
				return true;
			}
		}
		return false;
	}
	
	public void remove(int i) {
		for(int j=i;j<size-1;j++) {
			list[i]=list[i+1];
		}
		list[size]=null;
		size--;
	}
	
	public void update(int i , E object1) {
		list[i]=object1;
	}
	
}
