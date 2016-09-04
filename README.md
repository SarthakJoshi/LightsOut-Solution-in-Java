# LightsOut Solution in Java
This program solves LightsOut puzzle posted on Codeeval as a sponsored challenge in Java. It follows gaussian elimination method, which reduces matrix in GF(2) { values are either in 0 or 1} , into reduced row echelon form.

This program solves any m*n matrix and prints out the minimum number of moves required to solve the puzzle, or -1 if it's impossible to solve it.

Example Input : 
    
    4 10 ...OOOOOOO|.OO.O.O...|.OO..OO.OO|...O....O.
    3 3 ..O|OOO|OOO
    5 7 .O.O...|..O.O..|.O.O..O|.O..OOO|OO.OOOO

Example Output : 
    
    19
    2
    -1
    
Explanation : In 1st test case, 4 -> Number of rows ; 10 -> Number of columns ;  '.' -> toggle off ; 'O' -> toggle on ; '|' -> break between 2 consecutive rows, and minimum 19 moves required to solve the test case

P.S. I'll be uploading Test Cases in Junit and TestNG
