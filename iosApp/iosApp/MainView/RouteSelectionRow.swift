import SwiftUI
import shared

struct RouteSelectionRow: View {
    var title: String
    @Binding var selectedStation: PlainStation?
    var body: some View {
        HStack {
            Text(title)
                .bold()
                .font(Font.system(size: 24))
                .foregroundColor(.red)
            Text(selectedStation?.title ?? "Не выбрано")
                .foregroundColor(.black)
            Spacer()
        }
        .padding(.vertical, 15)
        .padding(.horizontal, 10)
    }
}

#if DEBUG
struct RouteSelectionRow_Previews: PreviewProvider {
    static var previews: some View {
        RouteSelectionRow(title: "A", selectedStation: .constant(nil))
    }
}
#endif
