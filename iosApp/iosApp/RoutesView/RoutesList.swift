import SwiftUI
import shared

struct RoutesList: View {
    var route: SelectedRoute
    @ObservedObject var model: RoutesListViewModel

    init(route: SelectedRoute) {
        self.route = route
        self.model = RoutesListViewModel(selectedRoute: route)
    }

    var body: some View {
        objectsList
            .onAppear {
                model.onViewDidAppear()
            }
            .onDisappear {
                model.onViewDidDisappear()
            }
    }

    @ViewBuilder
    var objectsList: some View {
        if let errorText = model.errorText {
            Text(errorText)
                .foregroundColor(.red)
        } else {
            if let segments = model.segments {
                if segments.count == 0 {
                    Text("Ничего не найдено!")
                } else {
                    List(segments, id: \.id) { segment in
                        RouteView(route: segment)
                    }
                }
            } else {
                VStack {
                    Text("Загружаем расписание...")
                    ProgressView()
                }
            }
        }
    }
}

struct RoutesList_Previews: PreviewProvider {
    static var previews: some View {
        RoutesList(route: SelectedRoute(from: nil, to: nil))
    }
}

extension Segment {
    var id: String {
        return UUID().uuidString
    }
}
