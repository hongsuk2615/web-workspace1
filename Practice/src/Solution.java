class Solution {
	public static void main(String[] args) {
		int[] a = {2,3,3,5};
		int[] answer = solution(a);
		for(int i : answer) {
			System.out.println(i);
		}
		int j = 0;
		do{
			++j;
			System.out.println(j);
		}while(j<=5);

	}
    public static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        for(int index = 0 ; index < numbers.length - 1; index++){
            answer[index] = 뒷큰수(numbers[index], index, numbers);
        }
        answer[numbers.length-1] = -1;
        
        return answer;
    }
    
    public static int 뒷큰수(int value, int index, int[] numbers){
        for(int i = index+1; i < numbers.length ; i++){
            if(numbers[i] > value){
                return numbers[i];
            }
        }
        return -1;
    }
}