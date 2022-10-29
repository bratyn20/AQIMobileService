package com.example.googlemap1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class PageInfoFragment extends Fragment {

    private int pageNumber;

    public static PageInfoFragment newInstance(int page) {
        PageInfoFragment fragment = new PageInfoFragment();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public PageInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_info_page, container, false);
        //TextView pageHeader = result.findViewById(R.id.displayText);
        String header = "Фрагмент " + (pageNumber+1);
        if(pageNumber == 2) {
            ConstraintLayout constraintLayout = result.findViewById(R.id.info_page);

            TableLayout tableLayout = new TableLayout( getContext());
            TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableRowParams.setMargins(10,10,10,10);
            tableLayout.setBackgroundResource(R.drawable.rounded);
            tableLayout.setLayoutParams(tableRowParams);

            // первая строка
            TableRow tableRow1 = new TableRow(getContext());
            tableRow1.setBackgroundResource(R.drawable.rounded);

            TextView textView1 = new TextView(getContext());
            textView1.setText("Индекс качества воздуха");
            textView1.setTextSize(12);
            textView1.setTextColor(Color.BLACK);
            tableRow1.addView(textView1, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView2 = new TextView(getContext());
            textView2.setText("Уровеь загрязненности воздуха");
            textView2.setTextSize(12);
            textView2.setTextColor(Color.BLACK);
            tableRow1.addView(textView2,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView3 = new TextView(getContext());
            textView3.setText("Что это значит");
            textView3.setTextSize(12);
            textView3.setTextColor(Color.BLACK);
            tableRow1.addView(textView3,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));


            // вторая строка
            TableRow tableRow2 = new TableRow(getContext());
            tableRow2.setBackgroundColor(Color.GREEN);

            TextView textView4 = new TextView(getContext());
            textView4.setText("Хороший");
            textView4.setTextSize(12);
            textView4.setTextColor(Color.BLACK);
            tableRow2.addView(textView4, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView5 = new TextView(getContext());
            textView5.setText("от 0 до 50");
            textView5.setTextSize(12);
            textView5.setTextColor(Color.BLACK);
            tableRow2.addView(textView5,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView6 = new TextView(getContext());
            textView6.setText("Качество воздуха считается удовлетворительным, а загрязнение воздуха представляет незначительный или нулевой риск");
            textView6.setTextSize(12);
            textView6.setTextColor(Color.BLACK);
            tableRow2.addView(textView6,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 3 строка
            TableRow tableRow3 = new TableRow(getContext());
            tableRow3.setBackgroundColor(Color.YELLOW);

            TextView textView7 = new TextView(getContext());
            textView7.setText("Средний");
            textView7.setTextSize(12);
            textView7.setTextColor(Color.BLACK);
            tableRow3.addView(textView7, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView8 = new TextView(getContext());
            textView8.setText("от 51 до 100");
            textView8.setTextSize(12);
            textView8.setTextColor(Color.BLACK);
            tableRow3.addView(textView8,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView9 = new TextView(getContext());
            textView9.setText("Качество воздуха считается приемлимым;\n" +
                    "Однако, некоторые загрязняющие вещества могут вызывать умеренное беспокойство у небольшого числа людей, которые необычайно чувствительны к загрязнению");
            textView9.setTextSize(12);
            textView9.setTextColor(Color.BLACK);
            tableRow3.addView(textView9,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 4 строка
            TableRow tableRow4 = new TableRow(getContext());
            tableRow4.setBackgroundColor(Color.rgb(255, 165,0));

            TextView textView10 = new TextView(getContext());
            textView10.setText("Нездоровый для людей с повышенной чувствительностью к проблемам здоровья");
            textView10.setTextSize(12);
            textView10.setTextColor(Color.BLACK);
            tableRow4.addView(textView10, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView11 = new TextView(getContext());
            textView11.setText("от 101 до 150");
            textView11.setTextSize(12);
            textView11.setTextColor(Color.BLACK);
            tableRow4.addView(textView11,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView12 = new TextView(getContext());
            textView12.setText("Представители чувствительных групп могут испытывать последствия для здоровья.\n" +
                    "Широкую общественность, скорее всего, это не затронет");
            textView12.setTextSize(12);
            textView12.setTextColor(Color.BLACK);
            tableRow4.addView(textView12,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 5 строка
            TableRow tableRow5 = new TableRow(getContext());
            tableRow5.setBackgroundColor(Color.rgb(255, 185,185));

            TextView textView13 = new TextView(getContext());
            textView13.setText("Нездоровый");
            textView13.setTextSize(12);
            textView13.setTextColor(Color.BLACK);
            tableRow5.addView(textView13, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView14 = new TextView(getContext());
            textView14.setText("от 151 до 200");
            textView14.setTextSize(12);
            textView14.setTextColor(Color.BLACK);
            tableRow5.addView(textView14,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView15 = new TextView(getContext());
            textView15.setText("Все люди могут начать ощущать воздействие на здоровье; чувствительные люди могут быть подвержены большому воздействию.");
            textView15.setTextSize(12);
            textView15.setTextColor(Color.BLACK);
            tableRow5.addView(textView15,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 6 строка
            TableRow tableRow6 = new TableRow(getContext());
            tableRow6.setBackgroundColor(Color.rgb(255, 124,245));

            TextView textView16 = new TextView(getContext());
            textView16.setText("Очень нездоровый");
            textView16.setTextSize(12);
            textView16.setTextColor(Color.BLACK);
            tableRow6.addView(textView16, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView17 = new TextView(getContext());
            textView17.setText("от 201 до 300");
            textView17.setTextSize(12);
            textView17.setTextColor(Color.BLACK);
            tableRow6.addView(textView17,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView18 = new TextView(getContext());
            textView18.setText("Предупреждения о черезвучайной опасности для здоровья. Скорее всего, будет затронуто всё население.");
            textView18.setTextSize(12);
            textView18.setTextColor(Color.BLACK);
            tableRow6.addView(textView18,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 7 строка
            TableRow tableRow7 = new TableRow(getContext());
            tableRow7.setBackgroundColor(Color.rgb(239, 107,115));

            TextView textView19 = new TextView(getContext());
            textView19.setText("Опасный");
            textView19.setTextSize(12);
            textView19.setTextColor(Color.BLACK);
            tableRow7.addView(textView19, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView20 = new TextView(getContext());
            textView20.setText("от 300+");
            textView20.setTextSize(12);
            textView20.setTextColor(Color.BLACK);
            tableRow7.addView(textView20,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView21 = new TextView(getContext());
            textView21.setText("Опасность для здоровья, каждый человек может испытывать более серьёзные последствия для здоровья");
            textView21.setTextSize(12);
            textView21.setTextColor(Color.BLACK);
            tableRow7.addView(textView21,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));


            tableLayout.addView(tableRow1);
            tableLayout.addView(tableRow2);
            tableLayout.addView(tableRow3);
            tableLayout.addView(tableRow4);
            tableLayout.addView(tableRow5);
            tableLayout.addView(tableRow6);
            tableLayout.addView(tableRow7);

            constraintLayout.addView(tableLayout);

        }

        if(pageNumber == 0){
            ConstraintLayout constraintLayout = result.findViewById(R.id.info_page);

            TableLayout tableLayout = new TableLayout( getContext());
            TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableRowParams.setMargins(10,10,10,10);
            tableLayout.setBackgroundResource(R.drawable.rounded);
            tableLayout.setLayoutParams(tableRowParams);

            // первая строка
            TableRow tableRow1 = new TableRow(getContext());
            tableRow1.setBackgroundResource(R.drawable.rounded);

            TextView textView1 = new TextView(getContext());
            textView1.setText("Температура");
            textView1.setTextSize(12);
            textView1.setTextColor(Color.BLACK);
            tableRow1.addView(textView1, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView2 = new TextView(getContext());
            textView2.setText("Значение в градусах цельсия");
            textView2.setTextSize(12);
            textView2.setTextColor(Color.BLACK);
            tableRow1.addView(textView2,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView3 = new TextView(getContext());
            textView3.setText("Что это значит");
            textView3.setTextSize(12);
            textView3.setTextColor(Color.BLACK);
            tableRow1.addView(textView3,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // вторая строка
            TableRow tableRow2 = new TableRow(getContext());
            tableRow2.setBackgroundColor(Color.GREEN);

            TextView textView4 = new TextView(getContext());
            textView4.setText("Холодный");
            textView4.setTextSize(12);
            textView4.setTextColor(Color.BLACK);
            tableRow2.addView(textView4, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView5 = new TextView(getContext());
            textView5.setText("от -40 до 0");
            textView5.setTextSize(12);
            textView5.setTextColor(Color.BLACK);
            tableRow2.addView(textView5,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView6 = new TextView(getContext());
            textView6.setText("При данной температуре следует тепло одеваться, одевать перчатки, шапку, даже при занятиях спортом");
            textView6.setTextSize(12);
            textView6.setTextColor(Color.BLACK);
            tableRow2.addView(textView6,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 3 строка
            TableRow tableRow3 = new TableRow(getContext());
            tableRow3.setBackgroundColor(Color.YELLOW);

            TextView textView7 = new TextView(getContext());
            textView7.setText("Средняя");
            textView7.setTextSize(12);
            textView7.setTextColor(Color.BLACK);
            tableRow3.addView(textView7, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView8 = new TextView(getContext());
            textView8.setText("от 0 до 18");
            textView8.setTextSize(12);
            textView8.setTextColor(Color.BLACK);
            tableRow3.addView(textView8,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView9 = new TextView(getContext());
            textView9.setText("Достаточно прохладно, если вы работаете при такой температуре, следует одевать перчатки, так как пальцы рук могут замерзать при длительном нахождении на улице, обзательно оденьте кофту");
            textView9.setTextSize(12);
            textView9.setTextColor(Color.BLACK);
            tableRow3.addView(textView9,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 4 строка
            TableRow tableRow4 = new TableRow(getContext());
            tableRow4.setBackgroundColor(Color.rgb(255, 165,0));

            TextView textView10 = new TextView(getContext());
            textView10.setText("Благоприятная температура");
            textView10.setTextSize(12);
            textView10.setTextColor(Color.BLACK);
            tableRow4.addView(textView10, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView11 = new TextView(getContext());
            textView11.setText("от 18 до 27 градусов");
            textView11.setTextSize(12);
            textView11.setTextColor(Color.BLACK);
            tableRow4.addView(textView11,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView12 = new TextView(getContext());
            textView12.setText("Данная температура благоприятна для каждого человека");
            textView12.setTextSize(12);
            textView12.setTextColor(Color.BLACK);
            tableRow4.addView(textView12,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 5 строка
            TableRow tableRow5 = new TableRow(getContext());
            tableRow5.setBackgroundColor(Color.rgb(255, 185,185));

            TextView textView13 = new TextView(getContext());
            textView13.setText("Высокая");
            textView13.setTextSize(12);
            textView13.setTextColor(Color.BLACK);
            tableRow5.addView(textView13, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView14 = new TextView(getContext());
            textView14.setText("от 27 до 40");
            textView14.setTextSize(12);
            textView14.setTextColor(Color.BLACK);
            tableRow5.addView(textView14,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView15 = new TextView(getContext());
            textView15.setText("Пейте больше жикости, при длительном нахождении на воздухе можно получить тепловой удар");
            textView15.setTextSize(12);
            textView15.setTextColor(Color.BLACK);
            tableRow5.addView(textView15,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));




            tableLayout.addView(tableRow1);
            tableLayout.addView(tableRow2);
            tableLayout.addView(tableRow3);
            tableLayout.addView(tableRow4);
            tableLayout.addView(tableRow5);

            constraintLayout.addView(tableLayout);
        }

        if(pageNumber == 1){
            ConstraintLayout constraintLayout = result.findViewById(R.id.info_page);

            TableLayout tableLayout = new TableLayout( getContext());
            TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableRowParams.setMargins(10,10,10,10);
            tableLayout.setBackgroundResource(R.drawable.rounded);
            tableLayout.setLayoutParams(tableRowParams);

            // первая строка
            TableRow tableRow1 = new TableRow(getContext());
            tableRow1.setBackgroundResource(R.drawable.rounded);

            TextView textView1 = new TextView(getContext());
            textView1.setText("Влажность воздуха");
            textView1.setTextSize(12);
            textView1.setTextColor(Color.BLACK);
            tableRow1.addView(textView1, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView2 = new TextView(getContext());
            textView2.setText("Значение в процентах");
            textView2.setTextSize(12);
            textView2.setTextColor(Color.BLACK);
            tableRow1.addView(textView2,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView3 = new TextView(getContext());
            textView3.setText("Что это значит");
            textView3.setTextSize(12);
            textView3.setTextColor(Color.BLACK);
            tableRow1.addView(textView3,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // вторая строка
            TableRow tableRow2 = new TableRow(getContext());
            tableRow2.setBackgroundColor(Color.rgb(102, 205, 170));

            TextView textView4 = new TextView(getContext());
            textView4.setText("Низкая влажность");
            textView4.setTextSize(12);
            textView4.setTextColor(Color.BLACK);
            tableRow2.addView(textView4, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView5 = new TextView(getContext());
            textView5.setText("от 0 до 30");
            textView5.setTextSize(12);
            textView5.setTextColor(Color.BLACK);
            tableRow2.addView(textView5,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView6 = new TextView(getContext());
            textView6.setText("Человек на большую часть состоит из жидкости и при понижении влажности в воздухе, его кожа начинает высыхать, а организм в целом получает обезвоживание. Слизистые оболочки пересыхают, начинают трескаться, что позволяет различным вирусам и бактериям проще проникать в организм.");
            textView6.setTextSize(12);
            textView6.setTextColor(Color.BLACK);
            tableRow2.addView(textView6,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 3 строка
            TableRow tableRow3 = new TableRow(getContext());
            tableRow3.setBackgroundColor(Color.GREEN);

            TextView textView7 = new TextView(getContext());
            textView7.setText("Комфортная влажность");
            textView7.setTextSize(12);
            textView7.setTextColor(Color.BLACK);
            tableRow3.addView(textView7, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView8 = new TextView(getContext());
            textView8.setText("от 30 до 60");
            textView8.setTextSize(12);
            textView8.setTextColor(Color.BLACK);
            tableRow3.addView(textView8,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView9 = new TextView(getContext());
            textView9.setText("Любой человек ощущает себя благоприятно");
            textView9.setTextSize(12);
            textView9.setTextColor(Color.BLACK);
            tableRow3.addView(textView9,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 4 строка
            TableRow tableRow4 = new TableRow(getContext());
            tableRow4.setBackgroundColor(Color.rgb(239, 107,115));

            TextView textView10 = new TextView(getContext());
            textView10.setText("Высокая влажность");
            textView10.setTextSize(12);
            textView10.setTextColor(Color.BLACK);
            tableRow4.addView(textView10, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView11 = new TextView(getContext());
            textView11.setText("то 60 до 100");
            textView11.setTextSize(12);
            textView11.setTextColor(Color.BLACK);
            tableRow4.addView(textView11,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView12 = new TextView(getContext());
            textView12.setText("Длительное воздействие этого фактора способствует снижению иммунитета. Особенно страдают люди с хроническими сердечно-сосудистыми заболеваниями, гипертонией, атеросклерозом, поскольку эти болезни в период повышенной влажности обостряются.");
            textView12.setTextSize(12);
            textView12.setTextColor(Color.BLACK);
            tableRow4.addView(textView12,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            tableLayout.addView(tableRow1);
            tableLayout.addView(tableRow2);
            tableLayout.addView(tableRow3);
            tableLayout.addView(tableRow4);

            constraintLayout.addView(tableLayout);
        }

        if(pageNumber == 3){
            ConstraintLayout constraintLayout = result.findViewById(R.id.info_page);

            TableLayout tableLayout = new TableLayout( getContext());
            TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableRowParams.setMargins(10,10,10,10);
            tableLayout.setBackgroundResource(R.drawable.rounded);
            tableLayout.setLayoutParams(tableRowParams);

            // первая строка
            TableRow tableRow1 = new TableRow(getContext());
            tableRow1.setBackgroundResource(R.drawable.rounded);

            TextView textView1 = new TextView(getContext());
            textView1.setText("Атмосферное давление");
            textView1.setTextSize(12);
            tableRow1.addView(textView1, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView2 = new TextView(getContext());
            textView2.setText("Уровень атмосферного давления в Гектопаскалях");
            textView2.setTextSize(12);
            tableRow1.addView(textView2,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView3 = new TextView(getContext());
            textView3.setText("Что это значит");
            textView3.setTextSize(12);
            tableRow1.addView(textView3,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            //вторая строка
            TableRow tableRow2 = new TableRow(getContext());
            tableRow2.setBackgroundColor(Color.rgb(102, 205, 170));

            TextView textView4 = new TextView(getContext());
            textView4.setText("Пониженное");
            textView4.setTextSize(12);
            tableRow2.addView(textView4, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView5 = new TextView(getContext());
            textView5.setText("от 960 до 995");
            textView5.setTextSize(12);
            tableRow2.addView(textView5,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView6 = new TextView(getContext());
            textView6.setText("Пониженное атмосферное давление вызывает снижение парциального давления во вдыхаемом воздухе, что приводит к гипоксии.\n" +
                    "Может ощущаться тяжесть в голове, головная боль, сонливость, нарушение координации движения, депрессия.");
            textView6.setTextSize(12);
            tableRow2.addView(textView6,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 3 строка
            TableRow tableRow3 = new TableRow(getContext());
            tableRow3.setBackgroundColor(Color.GREEN);

            TextView textView7 = new TextView(getContext());
            textView7.setText("Нормальное");
            textView7.setTextSize(12);
            tableRow3.addView(textView7, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView8 = new TextView(getContext());
            textView8.setText("от 995 до 1000");
            textView8.setTextSize(12);
            tableRow3.addView(textView8,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView9 = new TextView(getContext());
            textView9.setText("Если человек не метеочувствительный, то благоприятно себя чувствует");
            textView9.setTextSize(12);
            tableRow3.addView(textView9,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));

            // 4 строка
            TableRow tableRow4 = new TableRow(getContext());
            tableRow4.setBackgroundColor(Color.rgb(239, 107,115));

            TextView textView10 = new TextView(getContext());
            textView10.setText("Повышенное");
            textView10.setTextSize(12);
            tableRow4.addView(textView10, new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));


            TextView textView11 = new TextView(getContext());
            textView11.setText("от 1000 до 1060");
            textView11.setTextSize(12);
            tableRow4.addView(textView11,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

            TextView textView12 = new TextView(getContext());
            textView12.setText("Повышенное атмосферное давление характеризуется насыщением крови и тканей газами воздуха.\n" +
                    "Может наблюдаться ухудшение слуха, сухость слизистых оболочек, скачки артериального давлениея, учащение пульса и частоты дыхания.");
            textView12.setTextSize(12);
            tableRow4.addView(textView12,new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2.5f));


            tableLayout.addView(tableRow1);
            tableLayout.addView(tableRow2);
            tableLayout.addView(tableRow3);
            tableLayout.addView(tableRow4);

            constraintLayout.addView(tableLayout);
        }


        //pageHeader.setText(header);
        return result;
    }
}
