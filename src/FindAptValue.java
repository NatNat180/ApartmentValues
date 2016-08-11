import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FindAptValue {

	/*
	 * Take the apartment name, it's rating, and the amount of people who voted
	 * Find the apartment value by dividing it's votes by the rating Put that
	 * value into a HashMap, with it's associated apartment name
	 */
	public static Map<String, Double> findAptValue(String apartment, double rating, int votes) {

		Map<String, Double> aptNameValue = new HashMap<String, Double>();

		Double value = votes / rating;

		aptNameValue.put(apartment, value);

		return aptNameValue;
	}

	/*
	 * We want to return apartment names and their value in order from highest
	 * value to lowest, so we shall make a method that returns a
	 * LinkedHashMap<String, Double> by taking a HashMap containing List (a list
	 * of the apartment names and values)
	 */
	public static LinkedHashMap<String, Double> showBestApt(List<Map<String, Double>> apartmentCandidates) {

		// We are going to need to take the apartment values and sort them, so
		// let's first create a list for the apartment values we will sort
		List<Double> aptValues = new ArrayList<>();

		//And iterate through each map of the given list with an enhanced for-loop to find the values
		for (Map<String, Double> candidate : apartmentCandidates) {
			for (String aptName : candidate.keySet()) {
				Double aptValue = candidate.get(aptName);
				//finally, we'll add that value to our list created above
				aptValues.add(aptValue);
			}
		}

		//Now let's sort that aptValues list!
		Collections.sort(aptValues);

		/* BUT, the method sorts the list from lowest value to highest.
		 * We want the opposite, so let's create yet another list
		 * and reverse iterate through the first list, adding each value to the new ordered list
		 */
		List<Double> orderedAptValues = new ArrayList<>();
		for (int i = aptValues.size() - 1; i >= 0; i--) {
			orderedAptValues.add(aptValues.get(i));
		}

		/*
		 * Now that we have our properly ordered list, we are ready to accompany said list with their respective apartment names in a new map!
		 */
		LinkedHashMap<String, Double> sortedApartmentMap = new LinkedHashMap<>();

		/*
		 * Let's loop a bunch more now. 
		 * We start off by checking each value in the ordered list (using a loop of course),
		 */
		for (int i = 0; i < orderedAptValues.size(); i++) {
			//...use a similar loop to the one above where we obtain the taken apartment names and values...
			for (Map<String, Double> candidate : apartmentCandidates) {
				for (String aptName : candidate.keySet()) {
					Double aptValue = candidate.get(aptName);
					/*
					 * ...and if the given value matches the index value of our orderedAptList value,
					 * we add the given value with it's corresponding name to the sortedApt Map!
					 */
					if (aptValue == orderedAptValues.get(i)) {
						sortedApartmentMap.put(aptName, aptValue);
					}

				}
			}
		}
		//Voila! We now have a properly sorted apartment map to display
		System.out.println("---Showing Apartments In Order From Best To Worst---");

		return sortedApartmentMap;
	}

	public static void main(String[] args) {

		List<Map<String, Double>> apartmentList = new ArrayList<Map<String, Double>>();

		apartmentList.add(findAptValue("Sonterra", 3.4, 31));
		apartmentList.add(findAptValue("Verandah", 3.2, 24));
		apartmentList.add(findAptValue("Griffis", 3.1, 27));
		apartmentList.add(findAptValue("Colonial Grand - CC", 3.5, 20));
		apartmentList.add(findAptValue("Colonial Grand - CP", 3.3, 7));
		apartmentList.add(findAptValue("Tintara", 2.7, 40));
		apartmentList.add(findAptValue("Monterone", 2.4, 29));
		apartmentList.add(findAptValue("Abelia", 4.1, 16));

		System.out.println(showBestApt(apartmentList));

	}

}
