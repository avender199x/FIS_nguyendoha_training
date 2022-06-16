/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
public class PlayerDatabase {
    String authorList =
            "Agatha Christie," +
                    "Alan Patton," +
                    "Alexander Solzhenitsyn," +
                    "Arthur Conan Doyle," +
                    "Anthony Trollope," +
                    "Baroness Orczy," +
                    "Brendan Behan," +
                    "Brian Moore," +
                    "Boris Pasternik," +
                    "Charles Dickens," +
                    "Charlotte Bronte," +
                    "Dorothy Parker," +
                    "Emile Zola," +
                    "Frank O'Connor," +
                    "Geoffrey Chaucer," +
                    "George Eliot," +
                    "G. K. Chesterton," +
                    "Graham Green," +
                    "Henry James," +
                    "James Joyce," +
                    "J. M. Synge," +
                    "J. R. Tolkien," +
                    "Jane Austin," +
                    "Leo Tolstoy," +
                    "Liam O'Flaherty," +
                    "Marcel Proust," +
                    "Mark Twain," +
                    "Oscar Wilde," +
                    "O. Henry," +
                    "Samuel Beckett," +
                    "Sean O'Casey," +
                    "William Shakespeare," +
                    "William Makepeace Thackeray," +
                    "W. B. Yeats," +
                    "Wilkie Collins";


    /* TODO Practice 11-2. Declare an ArrayList here */
    private ArrayList<String> arrayList = new ArrayList<>();

    /* TODO Practice 11-2. Add Constructor here */
    public PlayerDatabase(String authorList) {
        this.authorList = authorList;
    }

    public PlayerDatabase() {
    }

    /* TODO Practice 11-2. Add getTeam() method here */

    public String getAuthorList() {
        return authorList;
    }
}
