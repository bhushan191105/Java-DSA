public class Solution{
    public List<List<Integer>> generate(int numRows){
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0 ; i<numRows ; i++){
            List<Integer> row = new ArrayList<>();
            row.add(1); //first Element

            for(int j = 1 ; j<i ;j++){
                int value = result.get(i-1).get(j-1) + result.get(i-1).get(j);
                row.add(value); // middle element
            }

            if(i>0){
                row.add(1); // last element
            }

            result.add(row);
        }
        return result;
    }
}