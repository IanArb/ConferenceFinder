//
//  ConferencesView.swift
//  iosApp
//
//  Created by Ian Arbuckle on 16/06/2020.
//

import SwiftUI
import common

@available(iOS 13.0, *)
struct ContentView: View {
    
    
    @ObservedObject var viewModel = ConferencesViewModel(repository: ConferencesRepository())
    
    var body: some View {
        NavigationView {
            List(viewModel.conference, id: \.name) {
                conference in
                ConferenceView(conference: conference)
            }
            .navigationBarTitle(Text("Conferences"), displayMode: .large)
            .onAppear(perform: {
                self.viewModel.fetchConferences()
            })
        }
    }
    
}

@available(iOS 13.0, *)
struct ConferenceView : View {
    var conference: Conference
  
    var body: some View {
        VStack {
                   AsyncImage(
                    url: URL(string: conference.logoUrl)!,
                       placeholder: Text("Loading ...")
                   )
                    .aspectRatio(contentMode: .fit)
                    
        
                   HStack {
                       VStack(alignment: .leading,
                       spacing: 10) {
                        
                        Text(conference.name)
                               .font(.headline)
                               .foregroundColor(.secondary)
                        HStack {
                            Image(uiImage: UIImage(systemName: "location")!)
                            
                            Text(conference.location.country.name + ", " + conference.location.country.city)
                                   .font(.caption)
                                   .foregroundColor(.secondary)
                        }
                    
                        HStack {
                            Image(uiImage: UIImage(systemName: "calendar")!)
                            
                            Text(conference.dates.startDate)
                            .font(.caption)
                            .foregroundColor(.secondary)
                        }
                        
                        
                        Text(conference.status)
                        .font(.caption)
                        .foregroundColor(.green)
                       }
                       .layoutPriority(100)
        
                       Spacer()
                   }
                   .padding()
               }
               .cornerRadius(10)
               .overlay(
                   RoundedRectangle(cornerRadius: 10)
                       .stroke(Color(.sRGB, red: 150/255, green: 150/255, blue: 150/255, opacity: 0.1), lineWidth: 1)
               )
               .padding([.top, .horizontal])
    }
}

struct ConferenceDetailView: View {
    
}

@available(iOS 13.0.0, *)
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
