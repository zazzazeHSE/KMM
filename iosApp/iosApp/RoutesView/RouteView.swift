import SwiftUI
import shared

struct RouteView: View {
    private let route: Segment

    init(route: Segment) {
        self.route = route
    }

    var body: some View {
        VStack {
            HStack {
                Text(route.thread.number)
                Spacer()
                HStack {
                    Text(route.thread.company.title)
                    if #available(iOS 15.0, *) {
                        AsyncImage(url: URL(string: route.thread.company.logo ?? "")) { image in
                            image
                                .resizable()
                                .scaledToFill()
                        } placeholder: {
                            ProgressView()
                        }
                        .frame(width: 30, height: 30, alignment: .center)
                        .cornerRadius(15)
                    } else {
                        // Fallback on earlier versions
                    }
                }
            }
            HStack {
                VStack {
                    Text(route.from.title)
                    Text(timeToHoursAndMinutes(time: route.arrival))
                }
                Spacer()
                Image(systemName: "airplane")
                Spacer()
                VStack {
                    Text(route.to.title)
                    Text(timeToHoursAndMinutes(time: route.departure))
                }
            }
        }.padding()
    }

    private func timeToHoursAndMinutes(time: String) -> String {
        let components = time.split(separator: ":")
        return [components[0], components[1]].joined(separator: ":")
    }
}

struct RouteView_Previews: PreviewProvider {
    static func testableSegment() -> Segment {
        return Segment(
            arrival: "06:05:00",
            departure: "06:50:00",
            thread: shared.Thread(
                number: "S7 1111",
                company: Company(
                    title: "S7",
                    logo: "https://yastat.net/s3/rasp/media/data/company/svg/S7.svg")
            ),
            from: SegmentStation(title: "Кольцово"),
            to: SegmentStation(title: "Шереметьево")
        )
    }

    static var previews: some View {
        RouteView(route: testableSegment())
    }
}
