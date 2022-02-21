import Foundation
import SwiftUI
import shared

class RoutesListViewModel: ObservableObject, IScheduleView {
    @Published var segments: [Segment]?
    @Published var errorText: String?
    let selectedRoute: SelectedRoute
    let presenter: SchedulePresenter = SchedulePresenter()

    init(selectedRoute: SelectedRoute) {
        self.selectedRoute = selectedRoute
    }

    func onViewDidAppear() {
        guard
            let from = selectedRoute.from,
            let to = selectedRoute.to
        else {
            return
        }
        presenter.attachView(view: self)
        presenter.loadRoutes(from: from.code, to: to.code)
    }

    func onViewDidDisappear() {
        segments = nil
        errorText = nil
    }

    func showSegments(segments: [Segment]) {
        self.errorText = nil
        self.segments = segments
    }

    func showError(errorText: String) {
        self.errorText = errorText
        self.segments = nil
    }
}
