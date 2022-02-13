import SwiftUI
import shared

struct ContentView: View {
    @State var route = SelectedRoute()
    @State var fromSelectionViewShows = false
    @State var toSelectionViewShows = false

    @StateObject var stationsPresenter = StationsPresenter()

	var body: some View {
        NavigationView {
            VStack {
                NavigationLink(
                    destination:
                        StationsList(
                            isShowing: $fromSelectionViewShows,
                            onStationSelect: { station in
                                    route.from = station
                                }
                            ),
                    isActive: $fromSelectionViewShows
                ) {
                    RouteSelectionRow(title: "From", selectedStation: $route.from)
                }
                NavigationLink(
                    destination:
                        StationsList(
                            isShowing: $toSelectionViewShows,
                            onStationSelect: { station in
                                    route.to = station
                                }
                            )
                        ,
                    isActive: $toSelectionViewShows
                ) {
                    RouteSelectionRow(title: "To", selectedStation: $route.to)
                }
                NavigationLink("Search", destination: RoutesList(route: route))
                .disabled(!route.canSearch)
                Spacer()
            }
            .navigationTitle("Найти маршрут")
            .navigationBarTitleDisplayMode(.large)
        }
        .environmentObject(stationsPresenter)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        Group {
            ContentView()
        }
	}
}
