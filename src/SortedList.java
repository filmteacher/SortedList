import java.util.ArrayList;

public class SortedList
{
    private static ArrayList<String> sortedList = new ArrayList<>();

    public static void insertItem(String item)
    {
        int index;
        index = findInsertPoint(item);
        sortedList.add(index, item);
    }

    private static int findInsertPoint (String item)
    {
        return searchList(item);
    }

    //binarySearch code refactored from integer binary sort in LinkedIn Learning Java Algorithms tutorial
    public static int searchList(String item)
    {
        int min;
        int max;
        int mid;
        int compare;
        String middle;

        min = 0;
        max = sortedList.size() - 1;

        while (min <= max)
        {
            mid = (min + max) / 2;
            middle = sortedList.get(mid);
            compare = item.compareToIgnoreCase(middle);
            if (compare == 0) {
                return mid;
            } else if (compare < 0) {
                max = mid -1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    public static ArrayList<String> getList() {
        return new ArrayList<>(sortedList);
    }
}
