/**
 * Copyright (C) 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dashbuilder.displayer.client;

import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.ListBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import org.dashbuilder.common.client.StringUtils;
import org.dashbuilder.displayer.DisplayerSettingsColumn;
import org.dashbuilder.displayer.DisplayerSettings;
import org.dashbuilder.displayer.Position;
import org.dashbuilder.displayer.client.widgets.ChartAttributesEditor;
import org.dashbuilder.displayer.client.widgets.CommonAttributesEditor;
import org.dashbuilder.displayer.client.widgets.XAxisChartAttributesEditor;
import org.dashbuilder.displayer.impl.DisplayerSettingsColumnImpl;

/**
 * Base editor for all x-axis based displayers.
 */
// TODO drop this after completing the generic settings editor
public class XAxisChartSettingsEditorBase extends AbstractDisplayerSettingsEditor {

    interface EditorBinder extends UiBinder<Widget, XAxisChartSettingsEditorBase>{}
    private static final EditorBinder uiBinder = GWT.create(EditorBinder.class);

    @UiField
    CommonAttributesEditor commonAttributesEditor;

    @UiField
    ChartAttributesEditor chartAttributesEditor;

    @UiField
    XAxisChartAttributesEditor xaxisChartAttributesEditor;

    public XAxisChartSettingsEditorBase() {

        // Init the editor from the UI Binder template
        initWidget(uiBinder.createAndBindUi(this));

        commonAttributesEditor.addShowTitleChangeHandler( new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange( ValueChangeEvent<Boolean> event ) {
                displayerSettings.setTitleVisible( event.getValue() );
                notifyChanges();
            }
        } );

        commonAttributesEditor.addTitleChangeHandler( new ValueChangeHandler<String>() {
            @Override
            public void onValueChange( ValueChangeEvent<String> event ) {
                String title = event.getValue();
                if ( title != null ) {
                    displayerSettings.setTitle( title );
                    notifyChanges();
                }
            }
        } );

        commonAttributesEditor.addColumnsChangeHandler( new ValueChangeHandler<String>() {
            @Override
            public void onValueChange( ValueChangeEvent<String> event ) {
                displayerSettings.getColumnList().clear();
                displayerSettings.getColumnList().addAll( parseColumns( event.getValue() ) );
                notifyChanges();
            }
        } );

        chartAttributesEditor.addChartWidthChangeHandler( new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange( ValueChangeEvent<Integer> event ) {
                int width = displayerSettings.getChartWidth();
                if ( event.getValue() != null ) width = event.getValue();
                displayerSettings.setChartWidth( width );
                notifyChanges();
            }
        } );

        chartAttributesEditor.addChartHeightChangeHandler( new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange( ValueChangeEvent<Integer> event ) {
                int height = displayerSettings.getChartHeight();
                if ( event.getValue() != null ) height = event.getValue();
                displayerSettings.setChartHeight( height );
                notifyChanges();
            }
        } );

        chartAttributesEditor.addChartTopMarginChangeHandler( new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange( ValueChangeEvent<Integer> event ) {
                int topMargin = displayerSettings.getChartMarginTop();
                if ( event.getValue() != null ) topMargin = event.getValue();
                displayerSettings.setChartMarginTop( topMargin );
                notifyChanges();
            }
        } );

        chartAttributesEditor.addChartBottomMarginChangeHandler( new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange( ValueChangeEvent<Integer> event ) {
                int bottomMargin = displayerSettings.getChartMarginBottom();
                if ( event.getValue() != null ) bottomMargin = event.getValue();
                displayerSettings.setChartMarginBottom( bottomMargin );
                notifyChanges();
            }
        } );

        chartAttributesEditor.addChartLeftMarginChangeHandler( new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange( ValueChangeEvent<Integer> event ) {
                int leftMargin = displayerSettings.getChartMarginLeft();
                if ( event.getValue() != null ) leftMargin = event.getValue();
                displayerSettings.setChartMarginLeft( leftMargin );
                notifyChanges();
            }
        } );

        chartAttributesEditor.addChartRightMarginChangeHandler( new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange( ValueChangeEvent<Integer> event ) {
                int rightMargin = displayerSettings.getChartMarginRight();
                if ( event.getValue() != null ) rightMargin = event.getValue();
                displayerSettings.setChartMarginRight( rightMargin );
                notifyChanges();
            }
        } );

        chartAttributesEditor.addShowLegendChangeHandler( new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange( ValueChangeEvent<Boolean> event ) {
                displayerSettings.setChartShowLegend( event.getValue() );
                notifyChanges();
            }
        } );

        chartAttributesEditor.addLegendPositionChangeHandler( new ChangeHandler() {
            @Override
            public void onChange( ChangeEvent event ) {
                // TODO try to uncouple the changehandler implementation from the underlying widget, in this case the listbox
                String selectedPosition = ( ( ListBox ) event.getSource() ).getValue();
                displayerSettings.setChartLegendPosition( Position.valueOf( selectedPosition ) );
                notifyChanges();
            }
        } );

        xaxisChartAttributesEditor.addXAxisShowLabelsChangeHandler( new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange( ValueChangeEvent<Boolean> event ) {
                displayerSettings.setXAxisShowLabels( event.getValue() );
                notifyChanges();
            }
        } );

        xaxisChartAttributesEditor.addXAxisAngleChangeHandler( new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange( ValueChangeEvent<Integer> event ) {
                int angle = displayerSettings.getXAxisLabelsAngle();
                if (event.getValue() != null ) angle = event.getValue();
                displayerSettings.setXAxisLabelsAngle( angle );
                notifyChanges();
            }
        } );

        xaxisChartAttributesEditor.addXAxisTitleChangeHandler( new ValueChangeHandler<String>() {
            @Override
            public void onValueChange( ValueChangeEvent<String> event ) {
                String title = event.getValue();
                if ( title != null ) {
                    displayerSettings.setXAxisTitle( title );
                    notifyChanges();
                }
            }
        } );
    }

    @Override
    public void setDisplayerSettings( DisplayerSettings displayerSettings ) {
        super.setDisplayerSettings( displayerSettings );
        commonAttributesEditor.setIsTitleVisible( displayerSettings.isTitleVisible() );
        commonAttributesEditor.setTitle( displayerSettings.getTitle() );
        commonAttributesEditor.setColumns( formatColumns( displayerSettings.getColumnList() ) );

        chartAttributesEditor.setChartWidth( displayerSettings.getChartWidth() );
        chartAttributesEditor.setChartHeight( displayerSettings.getChartHeight() );
        chartAttributesEditor.setChartTopMargin( displayerSettings.getChartMarginTop() );
        chartAttributesEditor.setChartBottomMargin( displayerSettings.getChartMarginBottom() );
        chartAttributesEditor.setChartLeftMargin( displayerSettings.getChartMarginLeft() );
        chartAttributesEditor.setChartRightMargin( displayerSettings.getChartMarginRight() );
        chartAttributesEditor.setChartShowLegend( displayerSettings.isChartShowLegend() );
        chartAttributesEditor.setChartLegendPosition( displayerSettings.getChartLegendPosition() );

        xaxisChartAttributesEditor.setXaxisShowLabels( displayerSettings.isXAxisShowLabels() );
        xaxisChartAttributesEditor.setXaxisLabelsAngle( displayerSettings.getXAxisLabelsAngle() );
        xaxisChartAttributesEditor.setXaxisTitle( displayerSettings.getXAxisTitle() );
    }

    private List<DisplayerSettingsColumn> parseColumns( String columns ) {
        if ( columns.length() > 0) {
            String[] sa = columns.split( "," );
            List<DisplayerSettingsColumn> l = new ArrayList<DisplayerSettingsColumn>( sa.length );
            for ( int i = 0; i < sa.length; i++ ) {
                DisplayerSettingsColumnImpl dsci = new DisplayerSettingsColumnImpl();
                String[] idAlias = sa[i].trim().split( ":" );
                if ( idAlias.length == 2 ) {
                    if ( StringUtils.isBlank( idAlias[ 0 ] ) && StringUtils.isBlank( idAlias[1] ) )
                        throw new IllegalArgumentException( "You must specify at least a column alias." );

                    if ( !StringUtils.isBlank( idAlias[1] ) ) {
                        dsci.setDisplayName( idAlias[ 1 ].trim() );
                    } else dsci.setDisplayName( idAlias[0].trim() );

                    if ( !StringUtils.isBlank( idAlias[0] ) ) dsci.setColumnId( idAlias[0].trim() );

                } else {
                    if ( !StringUtils.isBlank( idAlias[0] ) ) dsci.setDisplayName( idAlias[0].trim() );
                    else throw new IllegalArgumentException( "You must specify at least a column alias." );
                }
                l.add( dsci );
            }
            return l;
        }
        return new ArrayList<DisplayerSettingsColumn>();
    }

    private String formatColumns( List<DisplayerSettingsColumn> columns ) {
        StringBuilder sb = new StringBuilder( "" );
        if ( columns != null ) {
            for ( int i = 0; i < columns.size(); i++ ) {
                String columnId = columns.get( i ).getColumnId();
                if ( !StringUtils.isBlank( columnId ) ) {
                    sb.append( columnId ).append( ":" );
                }
                sb.append( columns.get( i ).getDisplayName() );
                if ( i != columns.size() -1 ) sb.append( "," );
            }
        }
        return sb.toString();
    }
}