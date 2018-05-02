/**
 *  Name: Quentin Linklater
 *  Class: 30/40S computer science
 *  teacher: Mr. Hardman
 *  Last time modified: 5/2/2018
 */
package comqlinklater.httpsgithub.contactbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static Contact[] contactsArray;
    private static int numContactsAdded;

    private EditText mNameInput;
    private EditText mPhoneInput;
    private EditText mEmailInput;

    private TextView mErrorMessage;
    private TextView mSortedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mErrorMessage = (TextView) findViewById(R.id.tv_error_message);
        mSortedList = (TextView) findViewById(R.id.tv_sorted_lists);

        mNameInput = (EditText) findViewById(R.id.et_name);
        mPhoneInput = (EditText) findViewById(R.id.et_phone_num);
        mEmailInput = (EditText) findViewById(R.id.et_email);

        contactsArray = new Contact[50];
        numContactsAdded = 0;

    }

    /**
     * addConToArray is the method that adds contacts into an array
     *
     * @param view is the view related to this method
     * @return Nothing is returned
     */
    public void addConToArray(View view)
    {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        Contact toAdd;

        if( numContactsAdded >= 50 )
        {
            mErrorMessage.setText( "You have added the maximum amount of contacts");
        }
        else
        {
            toAdd = new Contact( mNameInput.getText().toString(), mPhoneInput.getText().toString(), mEmailInput.getText().toString() );

            contactsArray[numContactsAdded] = toAdd;
            numContactsAdded++;

            mNameInput.setText("");
            mPhoneInput.setText("");
            mEmailInput.setText("");
            mNameInput.requestFocus();

            if( inputManager != null )
            {
                inputManager.showSoftInput( mNameInput, InputMethodManager.SHOW_IMPLICIT );
            }

            mErrorMessage.setText("Contact Added Successfully");
        }

    }

    /**
     * sortContact is the method that sorts contacts that is given and rearrange them in a ordinary fashion
     *
     * @param view is the view related to this method
     * @return Nothing is returned
     */
    public void sortContact(View view)
    {
        String sortedList = "";

        insertionSort();
        selectionSort();
        quickSort(contactsArray, 0, numContactsAdded - 1);

        for (int i = 0; i < numContactsAdded; i++) {
            if (contactsArray[i] != null) {
                sortedList += String.format("name: %20s\nPhone: %20s\nEmail: %20s\n\n", contactsArray[i].getName(), contactsArray[i].getPhone(), contactsArray[i].getEmail());
            }

        }

        mErrorMessage.setText("");
        mSortedList.setText(sortedList);

    }


    /**
     * insertionSort uses the insertionSort algorithm to sort a list of items in
     * ascending order
     *
     * @param "" there are no parameters
     * @return a String that displays the sorted list and how many steps it took
     */
    private void insertionSort() {


        //Key might need to be a different data type...
        Contact key;
        int index;

        //this is where insertion sort starts
        for (int j = 1; j < numContactsAdded; j++) {
            key = contactsArray[j];
            index = j - 1;

            while (index >= 0 && contactsArray[index].getName().compareTo(key.getName()) > 0) {
                contactsArray[index + 1] = contactsArray[index];
                index = index - 1;
            }

            contactsArray[index + 1] = key;
        }
    }

    /**
     * selectionSort uses the selection Sort algorithm to sort an list of items in ascending order
     *
     * @param "" there are no parameters
     * @return a String that will display the sorted list and how many steps it took
     */
    private void selectionSort() {

        int minIndex;
        Contact toSwap;

        for (int j = 0; j < numContactsAdded - 1; j++) {
            minIndex = j;

            for (int k = j + 1; k < numContactsAdded; k++)
            {
                if (contactsArray[k].getName().compareTo(contactsArray[minIndex].getName()) < 0) {
                    minIndex = k;
                }
            }

            toSwap = contactsArray[minIndex];
            contactsArray[minIndex] = contactsArray[j];
            contactsArray[j] = toSwap;
        }
    }

    /**
     * quickSort uses the quick sort algorithm to sort a list in ascending order
     *
     * @param array is the array we are sorting
     * @param low   is the beginning index of the section of the array we would like to sort
     * @param high  is the ending index of the section of the array we would like to sort
     * @return Nothing is returned
     */
    private void quickSort(Contact[] array, int low, int high) {
        int middle;
        Contact pivot;

        int i;
        int j;

        Contact toSwap;

        if (low < high) {
            middle = low + (high - low) / 2;
            pivot = array[middle];

            i = low;
            j = high;

            while (i <= j) {
                while (array[i].getName().compareTo(pivot.getName()) < 0) {
                    i++;
                }

                while (array[j].getName().compareTo(pivot.getName()) > 0) {
                    j--;
                }

                if (i <= j) {
                    toSwap = array[i];
                    array[i] = array[j];
                    array[j] = toSwap;
                    i++;
                    j--;
                }
            }

            if (low < j) {
                quickSort(array, low, j);
            }

            if (high > i) {
                quickSort(array, i, high);
            }
        }
    }
}
