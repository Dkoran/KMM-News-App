//
//  NewsViewCell.swift
//  iosApp
//
//  Created by Daniel Koranteng on 04/11/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit

class NewsViewCell: UITableViewCell {
    @IBOutlet weak var articleImage: UIImageView!
    
    @IBOutlet weak var labelArticle: UILabel!
    @IBOutlet weak var labelDate: UILabel!
    @IBOutlet weak var labelCategory: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

}
