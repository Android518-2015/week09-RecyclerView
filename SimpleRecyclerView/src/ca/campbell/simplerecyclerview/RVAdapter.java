package ca.campbell.simplerecyclerview;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PViewHolder> {
	List<Person> people;

	RVAdapter(List<Person> people) {
		this.people = people;
	}

	public static class PViewHolder extends RecyclerView.ViewHolder {
		CardView cv;
		TextView name, age;
		ImageView photo;

		public PViewHolder(View itemView) {
			super(itemView);
			cv = (CardView) itemView.findViewById(R.id.cv);
			name = (TextView) itemView.findViewById(R.id.name);
			age = (TextView) itemView.findViewById(R.id.age);
			photo = (ImageView) itemView.findViewById(R.id.photo);
		}

	} // PViewHolder

	public String getName(int pos) {
		return people.get(pos).name;
	}

	public String getUri(int pos) {
		return people.get(pos).wikipedia;
	}

	@Override
	public int getItemCount() {
		return people.size();
	}

	@Override
	public void onBindViewHolder(PViewHolder pvh, int i) {
		pvh.name.setText(people.get(i).name);
		pvh.age.setText("age " + people.get(i).age);
		pvh.photo.setImageResource(people.get(i).photo);
		pvh.itemView.setTag((Integer) i);

		pvh.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Integer pos = (Integer) v.getTag();
				Toast.makeText(v.getContext(), "Click " + getName(pos), Toast.LENGTH_SHORT).show();
			}
		});

		pvh.itemView.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				Integer pos = (Integer) v.getTag();
				if (getUri(pos).compareTo("none") != 0) {
					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getUri(pos)));
					try {
						v.getContext().startActivity(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return true; // I consumed the click
			}
		});

	} // onBindViewHolder()

	@Override
	public PViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.person_view, viewGroup, false);
		PViewHolder pvh = new PViewHolder(v);
		return pvh;
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

} // RVAdapter
