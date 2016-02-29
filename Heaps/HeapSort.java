import heaps.MyHeap;
public class HeapSort {
	
	MyHeap heap;
	public HeapSort(int a) {
		heap = new MyHeap(a);
	}

	public void heapSort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			heap.extractMax(a);
		}	
	}
}
