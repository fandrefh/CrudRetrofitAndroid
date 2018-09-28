package br.senac.pi.meudinheiro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.senac.pi.meudinheiro.R;
import br.senac.pi.meudinheiro.models.Category;

public class CategoryAdapter extends BaseAdapter {

    private List<Category> categories;
    private Context context;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categories.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = layoutInflater.inflate(R.layout.item_categories, parent,
                false);
        Category category = categories.get(position);

        TextView txtCatName = itemView.findViewById(R.id.txt_cat_name);
        txtCatName.setText(category.getName());

        return itemView;
    }
}
