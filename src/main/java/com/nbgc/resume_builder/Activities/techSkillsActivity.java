package com.nbgc.resume_builder.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.resume_builder.R;
import com.nbgc.resume_builder.Adapters.SkillAdapter;
import com.nbgc.resume_builder.Data.TechSkillsData;
import com.nbgc.resume_builder.Model.TechSkills;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class techSkillsActivity extends AppCompatActivity {

    @BindView(R.id.skillslist)
    ListView skillsList;
    @BindView(R.id.tskillText)
    TextView textView;
    @BindViews({R.id.addtitle, R.id.addsubtitle})
    List<TextInputEditText> editText;
    private int skillCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_skills);
        ButterKnife.bind(this);
        registerForContextMenu(skillsList);
        textView.setTypeface(fontSelector.font);
        if (TechSkillsData.techSkillsList.size() > 0) {
            SkillAdapter adapter = new SkillAdapter(this, TechSkillsData.techSkillsList);
            skillsList.setAdapter(adapter);
        } else {
            editText.get(0).setText(R.string.skillTitle0);
            editText.get(1).setText(R.string.skillSub0);
            skillCount++;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.skillslist) {
            menu.setHeaderIcon(R.drawable.ic_delete);
            menu.setHeaderTitle("Delete this Item?");
            menu.add(0, v.getId(), 0, "Yes");
            menu.add(0, v.getId(), 0, "Cancel");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selected = info.position;
        if (item.getTitle() == "Yes") {
            TechSkillsData.techSkillsList.remove(selected);
            skillCount--;
            SkillAdapter adapter = new SkillAdapter(this, TechSkillsData.techSkillsList);
            skillsList.setAdapter(adapter);
        } else {
            return false;
        }
        return true;
    }

    @OnClick(R.id.addButton)
    public void addSkills() {
        String title = editText.get(0).getText().toString();
        String subtitle = editText.get(1).getText().toString();
        if (TextUtils.isEmpty(title)) {
            editText.get(0).setError("Please Enter Title!");
            return;
        }
        if (TextUtils.isEmpty(subtitle)) {
            editText.get(1).setError("Please Enter SubTitle!");
            return;
        }
        TechSkillsData.techSkillsList.add(new TechSkills(title, subtitle));
        switch (skillCount) {
            case 0:
                editText.get(0).setText(R.string.skillTitle0);
                editText.get(1).setText(R.string.skillSub0);
                break;
            case 1:
                editText.get(0).setText(R.string.skillTitle1);
                editText.get(1).setText(R.string.skillSub1);
                break;
            case 2:
                editText.get(0).setText(R.string.skillTitle2);
                editText.get(1).setText(R.string.skillSub2);
                break;
            case 3:
                editText.get(0).setText(R.string.skillTitle3);
                editText.get(1).setText(R.string.skillSub3);
                break;
            case 4:
                editText.get(0).setText(R.string.skillTitle4);
                editText.get(1).setText(R.string.skillSub4);
                break;
            default:
                editText.get(0).setText("");
                editText.get(1).setText("");
                break;
        }
        skillCount++;
        SkillAdapter adapter = new SkillAdapter(this, TechSkillsData.techSkillsList);
        skillsList.setAdapter(adapter);
    }

    @OnClick(R.id.techsubmitButton)
    public void submitData() {
        Intent intent = new Intent(this, AcademiaActivity.class);
        startActivity(intent);
    }

}
