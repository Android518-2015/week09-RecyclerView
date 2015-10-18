package ca.campbell.simplerecyclerview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * This code is an example of using the RecyclerView modeled from
 * http://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-
 * cardview-on-android--cms-23465
 * 
 * @author P Campbell
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RecyclerView rv;
		RVAdapter adapter;
		LinearLayoutManager llm;
		List<Person> people;

		setContentView(R.layout.activity_main);
		people = initPeople();
		rv = (RecyclerView) findViewById(R.id.rv);
		// sure size won't change
		// this improves performance
		rv.setHasFixedSize(true);
		/*
		 * need a LayoutManager can extend RecyclerView.LayoutManager class
		 * simpler to use subclasses LinearLayoutManager (looks like a ListView)
		 * GridLayoutManager (looks like a GridView) StaggeredGridLayoutManager
		 */
		llm = new LinearLayoutManager(this);
		rv.setLayoutManager(llm);
		adapter = new RVAdapter(people);
		rv.setAdapter(adapter);

	}

	private List<Person> initPeople() {
		List<Person> people = new ArrayList<Person>();

		people.add(new Person("Grace Brewster Murray Hopper d", 86, R.drawable.gmh,
				"https://en.wikipedia.org/wiki/Grace_Hopper"));
		people.add(
				new Person("Jean Elaine Sammett", 87, R.drawable.jes, "https://en.wikipedia.org/wiki/Jean_E._Sammet"));
		people.add(new Person("Raida Perlman", 64, R.drawable.rp, "https://en.wikipedia.org/wiki/Radia_Perlman"));
		people.add(new Person("Frances Elizabeth Allen", 83, R.drawable.fea,
				"https://en.wikipedia.org/wiki/Frances_E._Allen"));

		return people;
	}
}
