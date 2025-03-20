---
layout: page
title: User Guide
---

TutorSynch is a **desktop app for managing student contacts and academic details, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TutorSynch can get your contact management or other administrative tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

2. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103-F15-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/cs4238`, `t/cs2103 t/GEA1000` etc.

* Any tags can be written as an alphanumeric tag, accompanied by `#` followed by 6 hexadecimal color code. (E.g. `CS2040C#ED9E49`)

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person : `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [l/EDU_LEVEL] [cy/CURRENT_YEAR] [cg/CURRENT_GRADE] [eg/EXP_GRADE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 cg/C+ t/cs2040s`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [l/EDU_LEVEL] [cy/CURRENT_YEAR] [cg/CURRENT_GRADE] [eg/EXP_GRADE] [t/TAG]… [t+/TAGS_TO_APPEND]… [t-/TAGS_TO_REMOVE]…`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.


#### Tag Editing : `t/`, `t+/`, `t-/`
* When editing tags, any number of `t/`, `t+/` or `t-/` may be provided, order of execution is as follows:
1. Tags prefixed with `t/` form the new list of tags (overwriting the old tags), if none are provided, old list of tags is used for the next steps.
2. Tags prefixed with `t+/` are added to the current list. If the tag already exists, the updated list remains unchanged as tags are unique.
3. Tags prefixed with `t-/` are removed from the list provided by the last step. If the tag to be removed does not exist, the app silently continues with the rest.
4. The final tag list is updated to the person.

Examples:
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 2 t-/Maths` Edits the tags of the 2nd person by removing `Maths` from existing list of tags.
*  `edit 1 t/Maths t/Science t-/Science ` Edits the tags of the 2nd person by clearing all existing tags and adding **only** `Maths`.
*  `edit 1 t+/friend t+/owesMoney` appends friend and owesMoney to existing tags (without overwriting or removing).


### Updating a person's payment information : `payment`

Updates the payment information of an existing person in the address book.

Format: `payment INDEX [f/FEE] [d/PAYMENT_DATE]`

* Updates the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* Existing values will be updated to the input values.
* If none of the optional fields are provided, the specified person's payment information will be removed.
* `PAYMENT_DATE` should be in the format [DD-MM-YYYY]

Examples:
* `payment 1 f/1000 d/14-11-2000` Updates the tutoring fee and payment date to be `1000` and `14-11-2000` respectively.
* `payment 2` Removes the payment information of the 2nd person.

### Locating persons by name : `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find lee yu` returns `Benny Lee`, `Bernice Yu`<br>
  ![result for 'find lee yu'](images/findLeeYuResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TutorSynch data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TutorSynch data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TutorSynch will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the TutorSynch to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TutorSynch home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action      | Format, Examples                                                                                                                                                                                                                                        |
|-------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**     | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [l/ EDUCATION_LEVEL] [cy/CURRENT_YEAR] [cg/CURRENT_GRADE] [eg/EXPECTED_GRADE] [t/TAG]…` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 cg/D t/friend t/colleague` |
| **Clear**   | `clear`                                                                                                                                                                                                                                                 |
| **Delete**  | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                     |
| **Edit**    | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [cy/CURRENT_YEAR] [cg/CURRENT_GRADE] [eg/EXPECTED_GRADE] [t/TAG]… [t+/TAGS_TO_APPEND]… [t-/TAGS_TO_REMOVE]…`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com t+/CS2040C#1E2C4D`         |
| **Payment** | `payment INDEX [f/FEE] d/[PAYMENT_DATE]`<br> e.g., `payment 4 f/1000 d/14-11-2000`                                                                                                                                                                      |
| **Find**    | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                                              |
| **List**    | `list`                                                                                                                                                                                                                                                  |
| **Help**    | `help`                                                                                                                                                                                                                                                  |

