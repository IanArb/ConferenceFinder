//
//  AsyncImage.swift
//  iosApp
//
//  Created by Ian Arbuckle on 17/06/2020.
//

import Foundation
import SwiftUI
import Combine

struct AsyncImage<Placeholder: View>: View {

    @ObservedObject private var loader: ImageLoader
    private let placeholder: Placeholder?
    
    init(url: URL, placeholder: Placeholder? = nil) {
        loader = ImageLoader(url: url)
        self.placeholder = placeholder
    }

    var body: some View {
        image
            .onAppear(perform: loader.load)
            .onDisappear(perform: loader.cancel)
    }
    
    private var image: some View {
        Group {
            if loader.image != nil {
                Image(uiImage: loader.image!)
                    .resizable()
                .scaledToFit()
                    .aspectRatio(contentMode: .fit)
            } else {
                placeholder
            }
        }
    }
    
    
    
}
