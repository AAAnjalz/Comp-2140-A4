/**
 * COMP 2140 Fall 2025
 * Standard set of dictionary operations
 * A. Miller, J. Vaughan
 */
public interface Dictionary {
    /**
     * Opens the file with the given name, and adds every word in thtat file
     * to the dictionary. You can assume each line contains exactly one word,
     * and that the file is not empty.
     * 
     * @param filename File to be opened
     */
    void open(String filename);

    /**
     * Searches the dictionary for the given string
     * 
     * @param key String to be searched for
     * @return True if this string is in the dictionary, false otherwise
     */
    boolean search(String key);

    /**
     * Adds the given string to the dictionary, if it is not already present.
     * If the string is already present, take no action.
     * 
     * @param key String to be added
     */
    void insert(String key);

    /**
     * Removes the given string from the dictionary, if it is present.
     * If the string is not present, take no action
     * 
     * @param key String to be deleted
     */
    void delete(String key);

    /**
     * Prints the contents of the dictionary to the console in a manner that shows
     * the status of the underlying data structure.
     * 
     * For the hash table with separate chaining: 
     *  - entries are printed in the order that they occur in the underlying array
     *  - entries in the same linked list appear on the same line
     *  - each linked list appears on a separate line
     *  - array positions that are empty are skipped
     * 
     * For the hash table with open addressing:
     *  - entries are printed in the order that they occur in the underlying array
     *  - each entry appears on a separate line
     *  - array positions that are empty or REMOVED are skipped
     * 
     * For the unbalanced binary search tree:
     *  - entries are printed in sorted order
     *  - each entry appears on a separate line
     *  - the root node entry appears furthest to the left
     *  - each node that is not the root is indented one step more than its parent
     */    
    void print();
}