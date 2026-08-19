class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int buyPrice = prices[0];

        for(int i = 1 ; i < prices.length ; i++ ){
            if(buyPrice < prices[i] ){
                int Profit = prices[i] - buyPrice ;
                if(Profit > maxProfit){
                    maxProfit = Profit ;
                }
            }else{
                buyPrice = prices[i];
            }
        }

        return maxProfit ;
    }
}