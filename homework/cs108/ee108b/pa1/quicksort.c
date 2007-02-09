/**********************************************************
* quick_sort.c 						          *
*                                                         *
* This is a program sorts using a simple quick sort       *
* algorithm from Programming Abstractions in C by Eric    *
* Roberts.								    *
**********************************************************/

void QuickSort(int* array, int n);
static int Partition (int* array, int n);


main()
{
   int *nums;
   int i;
   int array_size;
  
   printf("How many elements to be sorted?\n");
   scanf("%d", &array_size);

   nums = (int *) malloc(sizeof(int) * array_size);

   for (i = 0; i < array_size; i++)
   {
     printf("Enter next element:\n");
     scanf("%d", &(nums[i]));
   }

   QuickSort(nums, array_size);

   printf("The sorted list is:\n");
   for (i = 0; i < array_size; i++)
   {
     printf("%d ", nums[i]);
   }
   printf("\n");
}

void QuickSort(int* array, int n){
	int boundary;
		
	if (n<2) return;
	boundary = Partition(array, n);
	QuickSort(array, boundary);
	QuickSort(array + boundary + 1, n - boundary - 1);
}

static int Partition(int* array, int n){
	int lh, rh, pivot, temp;

	pivot = array[0];
	lh = 1;
	rh = n-1;
	while(1){
		while (lh < rh && array[rh] >= pivot) rh--;
		while(lh < rh && array[lh] < pivot) lh++;
		if (lh == rh) break;
		temp = array[lh];
		array[lh] = array[rh];
		array[rh] = temp;
	}
	if (array[lh] >= pivot) return (0);
	array[0] = array[lh];
	array[lh] = pivot;
	return (lh);
}
