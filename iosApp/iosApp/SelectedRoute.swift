import shared

struct SelectedRoute {
    var from: PlainStation?
    var to: PlainStation?

    var canSearch: Bool {
        from != nil && to != nil
    }
}
