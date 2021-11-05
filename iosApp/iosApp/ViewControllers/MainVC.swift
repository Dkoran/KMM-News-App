//
//  MainVC.swift
//  iosApp
//
//  Created by Daniel Koranteng on 04/11/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation


import UIKit

class MainV: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.disableDarkMode()
        setText()

        // Do any additional setup after loading the view.
    }
    
    func initializeFromStoryboardTabs () -> TabsViewController {
        let storyboard = UIStoryboard(name: "Tabs", bundle: nil)
        guard let controller = storyboard.instantiateViewController(withIdentifier: "TabsViewController") as? TabsViewController else { fatalError() }
            controller.modalPresentationStyle = .fullScreen
            self.present(controller, animated: true, completion: nil)

        return controller
    }
    @objc func setText() {
        
           tabBar.items?[0].title = "Home".localized()
           tabBar.items?[1].title = "Progress".localized()
           tabBar.items?[2].title = "More".localized()
       }
       
     

}
