# Machine Works Problem

## Problem description

You are the director of Arbitrarily Complex Machines (ACM for short), a company producing advanced machinery
using even more advanced machinery. The old production machinery has broken down, so you need to buy new
production machines for the company. Your goal is to make as much money as possible during the restructuring
period. During this period you will be able to buy and sell machines and operate them for profit while ACM owns
them. Due to space restrictions, ACM can own at most one machine at a time.
During the restructuring period, there will be several machines for sale. Being an expert in the advanced machines
market, you already know the price Pi and the availability day Di for each machines Mi. Note that if you do not buy
machine Mi on day Di, then somebody else will buy it and it will not be available later. Needless to say, you cannot
buy a machine if ACM has less money than the price of the machine.
If you buy a machine Mi on day Di, then ACM can operate it starting on day Di + 1. Each day that the machine
operates, it produces a profit of Gi dollars for the company.
You may decide to sell a machine to reclaim a part of its purchase price any day after you’ve bought it. Each machine
has a resale price Ri for which it may be resold to the market. You cannot operate a machine on the day that you sell
it, but you may sell a machine and use the proceeds to buy a new machine on the same day.
Once the restructuring period ends, ACM will sell any machine that it still owns. Your task is to maximize the amount
of money that ACM makes during the restructuring.

### Input
The input consists of several test cases. Each test case starts with a line containing three positive integers N, C, and
D. N is the number of machines for sale (N <= 105), C is the number of dollars with which the company begins the
restructuring (C <= 109), and D is the number of days that the restructuring lasts (D <= 109).
Each of the next N lines describes a single machine for sale. Each line contains four integers Di, Pi, Ri and Gi,
denoting (respectively) the day on which the machine is for sale, the dollar price for which it may be bought, the
dollar price for which it may be resold and the daily profit generated by operating the machine. These numbers satisfy
1 <= Di <= D, 1 <= Ri < Pi <= 109 and 1 <= Gi <= 109.
The last test case is followed by a line containing three zeros.

### Output
For each test case, display its case number followed by the largest number of dollars that ACM can have at the end of
day D + 1.

## Solution

We can't simply each day buy the best machine because sometimes it is preferable to wait for a certain time to buy a more profitable machine.

So we need to explore the state (choice) space of possible scenarios (succession of choices). My solution implements a BFS that tries to avoid useless exploration to find the best solution.

### Rules we can deduce from the text

To avoid useless exploration, we need to filter the possible choice at a given state with some dominance rules. After a careful reading of the subject, we can deduce these dominance rules:

- **We never sold our machine to buy a less profitable one:** By absurd if there were a better solution in with we trade our machine against a less profitable one, we could find a better solution where we keep our machine.
- **We don't sell our machine until it hasn't started to make profit:** There is always a better solution where we don't buy this machine or where we buy another machine.
- **If for a given machine "day of sale" + "number of days to be profitable" > "number of restructuring days" we could remove the machine from the catalog:** It never going to be profitable.

These simple dominance rules were implemented and significantly reduce the time to solve the problem for small instances (for <img src="https://render.githubusercontent.com/render/math?math=N \leq 100">, it's enough), but we need to go deeper for larger instances. I haven't implemented these other rules because the subject explicitly says <img src="https://render.githubusercontent.com/render/math?math=N \leq 100">, and, for small instances, it could slow down the search.

### More advanced rules

I have identified some more advanced dominance rules. Here is what I have found :

* **If for two different scenarios (branch of the tree) we sold our machine the same day, we can remove the branch with less money and no machine after the sale of the machine:** Future profit doesn't depend on the past machine if we have both sold our machine. We can always find a better solution because we can buy more machine compared to the other branch with less money.

  To implement this we need to keep the best selling state for each day and compare when we try to sell our machine if we are in the best-selling branch for a given day. Unfortunately, it slows down the search for small inputs.

* **If at a certain day we have a state with more money than initially (money = money in hand + value of the machine), we can stop exploring the state where we haven't bought a machine from the start to the date:** Because even if a good opportunity arrives in the future, it would always be better to use the scenario where we make some money and, after, sell the current machine to buy the better one.

* **If at a certain day we have a branch with more money (money in hand + resold value of the machine) and a more profitable machine than another branch at the same day, we can stop the exploration of the one with less money and a less profitable machine:** Because if we sold the machine, we still have more money and if we keep the machine, our money will increase more than the other machine.

## Additional instructions

### How to run ?

To run the program you first need to compile it:
javac Main.java

And then launch:
java Main instance.txt

### Content of the archive

- Main.java : the main class
- Machine.java : a model representing a machine
- Parser.java : the static class containing the method to parse the input file
- Problem.java : a model representing the Problem
- State.java : a model representing a state (multiples choices linked one to another).
- Solver.java : the static class containing the method to solve an instance of Problem
- instance.txt : the given instance to solve

### Solution for the small example instance

The solution for the instance of the problem is the following:
Case 1: 44
Case 2: 11
Case 3: 12
Case 4: 10
Case 5: 39
Case 6: 39