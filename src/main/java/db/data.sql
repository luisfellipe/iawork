/**
 * Author:  luis felipe
 * Created: 23 de out de 2019
 */

create database iaworkdata;
--6 Attributes in total (1 goal field, 1 non-predictive, 4 predictive attributes)
create table data(
 int BIRADS, --(1 - 5) -- 
 age int,
 shape int, -- mass shape: round=1 oval=2 lobular=3 irregular=4 (nominal)
 margin int, --  mass margin: circumscribed=1 microlobulated=2 obscured=3 ill-defined=4 spiculated=5 (nominal)
density int, -- mass density high=1 iso=2 low=3 fat-containing=4 (ordinal)
severety int -- benign=0 or malignant=1 (binominal, goal field!)
);

/*
 Missing Attribute Values:
- BI-RADS assessment: 2
- Age: 5
- Shape: 31
- Margin: 48
- Density: 76
- Severity: 0

*/
