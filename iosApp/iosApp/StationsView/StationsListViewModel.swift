import Foundation
import SwiftUI
import shared

class StationsListViewModel: ObservableObject, IStationsListView {
    func showError(errorText: String) {
        self.stations = nil
        self.error = errorText
    }

    @Published var stations: [PlainStation]? = []

    var presenter: StationsPresenter?
    var error: String?

    func onViewDidAppear() {
        presenter?.attachView(view: self)
        presenter?.loadStations()
    }

    func presentStation(stations: [PlainStation]) {
        error = nil
        self.stations = stations
    }
}

extension StationsPresenter: ObservableObject {

}
