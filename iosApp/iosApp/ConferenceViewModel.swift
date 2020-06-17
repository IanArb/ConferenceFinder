//
//  ConferenceViewModel.swift
//  iosApp
//
//  Created by Ian Arbuckle on 16/06/2020.
//

import Foundation
import common

@available(iOS 13.0, *)
class ConferencesViewModel: ObservableObject {
    
    @Published var conference = [Conference]()
    
    private let repository: ConferencesRepository

    init(repository: ConferencesRepository) {
        self.repository = repository
    }
    
    func fetchConferences() {
        repository.fetchConferences(success: { data in
            self.conference = data
        })
    }
}
