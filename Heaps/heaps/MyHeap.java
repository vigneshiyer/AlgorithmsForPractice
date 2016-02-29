package heaps;
public class MyHeap {
	int heapsize;
	
	public MyHeap(int heapsize) {
		this.heapsize = heapsize;
	}
	
	private int left(int i) {
		return 2*i+1;
	}

	private int right(int i) {
		return left(i)+1;
	}

	private void buildMaxHeap(int [] a) {
		int l = heapsize/2;
		for (int i = l; i >= 0; i--) {
			maxHeapify(a,i);
		}
	}

	public int extractMax(int[] a) {
		buildMaxHeap(a);
		int t = a[0];
		a[0] = a[heapsize-1];
		a[heapsize-1] = t;
		heapsize--;
		return t;
	}

	private void maxHeapify (int[] a, int i) {
		int l = left(i);
		int r = right(i);
		
		int largest;
		if (l < heapsize && a[l] > a[i]) {
			largest = l;	
		}
		else {
			largest = i;
		}

		if (r < heapsize && a[r] > a[largest]) {
			largest = r;
		}

		if (largest != i) {
			int t = a[largest];
			a[largest] = a[i];
			a[i] = t;
			maxHeapify(a,largest);
		}

	}
	
}
