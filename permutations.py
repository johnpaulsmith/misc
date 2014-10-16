#Author: John Paul Smith
#
# These are two approaches for generating all possible permutations of a given
# input sequence of characters. This is meant as a demonstration of order (n!)
# growth as generating the permutations of even just the alphabet would result
# in 26! = 403,291,461,126,605,635,584,000,000 permutations. This code might be
# useful in situations where you need a quick way to generate all permutations
# of a very small set (ie. less than 10 elements).
#
# Between the two approaches, the iterative approach is faster and does not
# generate redundant permutations on input with repeat elements (the input of
# "aaa" will only produce one single permutation). The one caveat is that the
# data set needs to be sorted for the algorithm to work correctly, so this is
# another algorithm for which sorting is a fundamental sub-problem of a larger
# algorithm. Don Knuth called this one simply "Algorithm L".
#
# The recursive approach is fairly inferior and mostly there to answer the
# inevitable "but what about a recursive solution?" question. It isn't
# asymptotically slower but the overhead of removing items from one list and
# appending them to another slows it down by significant constant factor.
#


# A recursive method for generating and printing all permutations of a given
# character list. This method will generate redundant permutations if the input
# list contains duplicate characters.

def rec_perms(a, b=[]):    
    # If a is an empty list, then b is a permutation of the original input.
    if not a:
        #TODO: the desired action to take on the permutation.
        #Eg. print(b) or write b out to a file.        
        return
    # For each character in a, remove it and append it to b, then recurse. 
    for i in range(0, len(a)):        
        q = b[:]
        q.append(a[i])
        t = a[0:i] + a[i + 1:]        
        rec_perms(t, q) 

# An iterative method for generating and printing all unique permutations
# of a given character list.
def unique_perms(a):
    if len(a) < 2:
        #TODO: the desired action to take on the permutation.
        #Eg. print(a) or write a out to a file.
        return
    #The data set must be sorted for the algorithm to perform correctly.
    a = sorted(a)
    while True:
        #TODO: the desired action to take on the permutation.
        #Eg. print(a) or write a out to a file.
        
        # Set i as the index of the second to the last element.
        i = len(a) - 2
        # Decrement i until a[i] < a[i + 1].
        # Return if i < 0 (tests if the list is reverse-sorted).
        while a[i] >= a[i + 1]:
            i -= 1
            if i < 0:
                return
        # Set j as the index of the last element
        j = len(a) - 1
        # Decrement j until a[i] < a[j]
        while a[i] >= a[j]:
            j -= 1
        # Exchange a[i] with a[j]
        t = a[i]
        a[i] = a[j]
        a[j] = t
        # Reverse the order of all elements above a[i]
        j = len(a) - 1
        k = i + 1
        while k < j:
            t = a[k]
            a[k] = a[j]
            a[j] = t
            k += 1
            j -= 1

#original_input = "abcdefghijklmnopqrstuvwxyz"
#unique_perms(list(original_input))