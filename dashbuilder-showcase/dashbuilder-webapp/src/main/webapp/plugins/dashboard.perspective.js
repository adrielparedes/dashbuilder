$registerPerspective({
    id: "Dashboard",
    roles: [ "admins", "users" ],
    panel_type: "root_tab",
    view: {
        parts: [
            {
                place: "KPIPresenter",
                parameters: {"kpi": "sample0", "token" : "3"}
            }
        ],
        panels: [
            {
                width: 370,
                height: 340,
                position: "north",
                panel_type: "static",
                parts: [
                    {
                        place: "KPIPresenter",
                        parameters: {"kpi": "sample1", "token" : "2"}
                    }
                ]
            },
            {
                width: 570,
                height: 340,
                position: "south",
                panel_type: "multi_tab",
                parts: [
                    {
                        place: "KPIPresenter",
                        parameters: {"kpi": "sample1", "token" : "4"}
                    },
                    {
                        place: "KPIPresenter",
                        parameters: {"kpi": "sample0", "token" : "6"}
                    }
                ],
                panels: [
                    {
                        width: 370,
                        height: 340,
                        position: "north",
                        panel_type: "static",
                        parts: [
                            {
                                place: "KPIPresenter",
                                parameters: {"kpi": "sample1", "token" : "7"}
                            }
                        ]
                    }
                ]

            },
            {
                width: 520,
                height: 340,
                position: "east",
                panel_type: "simple_dnd",
                parts: [
                    {
                        place: "KPIPresenter",
                        parameters: {"kpi": "sample0", "token" : "5"}
                    }
                ]
            },
            {
                width: 700,
                min_width: 330,
                position: "west",
                panel_type: "simple_dnd",
                parts: [
                    {
                        place: "KPIPresenter",
                        parameters: {"kpi": "sample1", "token" : "0"}
                    }
                ]
            }
        ]
    }
});