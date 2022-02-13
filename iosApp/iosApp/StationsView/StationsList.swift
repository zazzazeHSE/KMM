import SwiftUI
import shared

struct StationsList: View {
    @EnvironmentObject var presenter: StationsPresenter
    @ObservedObject var model = StationsListViewModel()
    @Binding var isShowing: Bool
    @State var searchText = ""
    @State var showCancelButton = false
    var onStationSelect: (PlainStation) -> Void

    var body: some View {
        listView
            .onAppear {
                model.presenter = presenter
                model.onViewDidAppear()
            }
    }

    @ViewBuilder
    var listView: some View {
        if
            let stations = model.stations,
            stations.isEmpty
        {
            ProgressView()
        } else if let error = model.error {
            Text(error)
                .foregroundColor(.red)
        } else {
            objectsListView
        }
    }

    var objectsListView: some View {
        let stations = (model.stations ?? []).filter { $0.title.lowercased().starts(with: searchText.lowercased()) }
        return VStack {
            searchView
            List(stations, id: \.title) { station in
                Text(station.title).onTapGesture {
                    onStationSelect(station)
                    isShowing = false
                }
            }
        }
    }

    var searchView: some View {
        HStack {
            HStack {
                Image(systemName: "magnifyingglass")

                TextField("search", text: $searchText, onEditingChanged: { isEditing in
                    self.showCancelButton = true
                }, onCommit: {
                    print("onCommit")
                }).foregroundColor(.primary)

                Button(action: {
                    self.searchText = ""
                }) {
                    Image(systemName: "xmark.circle.fill").opacity(searchText == "" ? 0 : 1)
                }
            }
            .padding(EdgeInsets(top: 8, leading: 6, bottom: 8, trailing: 6))
            .foregroundColor(.secondary)
            .background(Color(.secondarySystemBackground))
            .cornerRadius(10.0)

            if showCancelButton  {
                Button("Cancel") {
                        UIApplication.shared.endEditing(true) // this must be placed before the other commands here
                        self.searchText = ""
                        self.showCancelButton = false
                }
                .foregroundColor(Color(.systemBlue))
            }
        }
        .padding(.horizontal)
        .navigationBarHidden(showCancelButton)
    }
}

struct StationsList_Previews: PreviewProvider {
    static var previews: some View {
        StationsList(isShowing: .constant(true), onStationSelect: { _ in })
    }
}

extension UIApplication {
    func endEditing(_ force: Bool) {
        self.windows
            .filter{$0.isKeyWindow}
            .first?
            .endEditing(force)
    }
}
