
    function buff(名称, 属性, 数值, 需要观察, 初始开关) {
        self = this;
        self.名称 = 名称;
        self.isActive = ko.observable(初始开关);
        if (需要观察) {
            self.数值 = ko.observable(数值);
            self.属性 = ko.observable(属性);
        }
        else {
            self.数值 = 数值;
            self.属性 = 属性;
        }
    }

    function 内置buff(buff) {
        var self = this;
        self.buff = buff;
        //移出来
    }

    function 抗性乘区(value) {
        var result = 0;
        if (value < 0) {
            result = 1 - value / 2;
        } else {
            if (value < 0.75) {
                result = 1 - value;
            } else {
                result = 1 / (1 + 4 * value);
            }
        }
        // TODELETE
        return result;
    }

    function ViewModel() {
        var self = this;
        self.基础攻击 = ko.observable(600);
        self.攻击力 = ko.observable(1500);
        self.暴击率 = ko.observable(50);
        self.暴击伤害 = ko.observable(100);
        self.元素伤害 = ko.observable(50);
        self.元素精通 = ko.observable(100);

        self.角色等级 = ko.observable(80);
        self.怪物等级 = ko.observable(80);
        self.怪物抗性 = ko.observable(10); // 记得除100
        self.减抗效果 = ko.observable(0);

        self.技能倍率 = ko.observable(130); // 记得除100

        // 计算基础攻击
        self.武器基础攻击 = ko.observable(608);
        self.人物基础攻击 = ko.observable(335);
        self.羽毛固定攻击力 = ko.observable(311);
        self.副词条固定攻击力 = ko.observable(50);

        self.攻击力百分比 = ko.computed(function () {
            return (Number(self.攻击力()) - Number(self.羽毛固定攻击力()) - Number(self.副词条固定攻击力())) / self.基础攻击();
        });

        self.新的基础攻击 = ko.computed(function () {
            return Number(self.武器基础攻击()) + Number(self.人物基础攻击());
        });
        self.新武器总攻击力 = ko.computed(function () {
            return self.新的基础攻击() * self.攻击力百分比() + Number(self.羽毛固定攻击力()) + Number(self.副词条固定攻击力());
        });

        self.提交攻击力 = function() {
            self.攻击力(self.新武器总攻击力().toFixed(0));
            self.基础攻击(self.新的基础攻击());
        }

        // 属性种类
        self.属性类型列表 = [
            { 属性: "攻击力%", 等价数值: 0.052, 百分比: true },  //0
            { 属性: "攻击力", 等价数值: 18, 百分比: false },   //1
            { 属性: "暴击率%", 等价数值: 0.035, 百分比: true },   //2
            { 属性: "暴击伤害%", 等价数值: 0.07, 百分比: true }, //3
            { 属性: "元素伤害%", 等价数值: 0.052, 百分比: true }, //4
            { 属性: "元素精通", 等价数值: 21, 百分比: false }, //5
        ]

        // 输出方式
        self.输出方式列表 = [
            { 输出方式: "火C 蒸发", 倍率: 1.5 }, //0
            { 输出方式: "水C 蒸发", 倍率: 2 },   //1
            { 输出方式: "火C 融化", 倍率: 2 },   //2
            { 输出方式: "冰C 融化", 倍率: 1.5 }, //3
            { 输出方式: "直伤", 倍率: 1 }   //4
        ]
        self.输出方式 = ko.observable(self.输出方式列表[0]);

        // 自定义buff
        self.buff列表 = ko.observableArray([new buff("班尼特Q", self.属性类型列表[1], 630, true, false)]);

        // 这里用isActive() 似乎值是不会改变的,应该指向函数本身
        self.内置buff列表 = [
            new 内置buff(new buff("双火", self.属性类型列表[0], 25, false, false)),
            new 内置buff(new buff("双岩", self.属性类型列表[4], 15, false, false)),
            new 内置buff(new buff("教官套", self.属性类型列表[5], 120, false, false)),
        ];

        self.buff攻击力 = ko.computed(function () {
            return Number(self.基础攻击()) * 统计buff(self.属性类型列表[0]) / 100 + Number(self.攻击力()) + 统计buff(self.属性类型列表[1]);
        });

        self.buff暴击率 = ko.computed(function () {
            return Number(self.暴击率()) + 统计buff(self.属性类型列表[2]);
        });

        self.buff暴击伤害 = ko.computed(function () {
            return Number(self.暴击伤害()) + 统计buff(self.属性类型列表[3]);
        });

        self.buff元素伤害 = ko.computed(function () {
            return Number(self.元素伤害()) + 统计buff(self.属性类型列表[4]);
        });

        self.buff元素精通 = ko.computed(function () {
            return Number(self.元素精通()) + 统计buff(self.属性类型列表[5]);
        });

        //添加buff
        self.addBuff = function () {
            self.buff列表.push(new buff("", self.属性类型列表[0], 0, true, true));

            var element = self.buff列表()[0];
        }
        //移除buff
        self.removeBuff = function (buff) { self.buff列表.remove(buff) }

        self.伤害期望面板开关 = ko.observable(true);
        self.新武器面板开关 = ko.observable(false);
        self.抗性面板开关 = ko.observable(false);

        self.检查伤害期望面板 = function () {
            var toggle = $("#伤害面板").hasClass('collapsed');
            self.伤害期望面板开关(toggle);
        };
        self.检查新武器面板 = function () {
            var toggle = $("#新武器面板").hasClass('collapsed');
            self.新武器面板开关(toggle);
        };
        self.检查抗性面板 = function () {
            var toggle = $("#抗性面板").hasClass('collapsed');
            self.抗性面板开关(toggle);
        };

        self.伤害期望面板样式 = ko.computed(function () {
            return self.伤害期望面板开关() ? "glyphicon-menu-up" : "glyphicon-menu-down";
        });
        self.新武器面板样式 = ko.computed(function () {
            return self.新武器面板开关() ? "glyphicon-menu-up" : "glyphicon-menu-down";
        });
        self.抗性面板样式 = ko.computed(function () {
            return self.抗性面板开关() ? "glyphicon-menu-up" : "glyphicon-menu-down";
        });

        self.toggleActive = function (isActive) {
            isActive(!isActive());
            console.log(isActive());
        }

        function 统计buff(buff类别) {
            //var在函数内定义是局部变量, let在{}代码块中是局部变量(而var不是)
            var sum = 0;
            for (let i = 0; i < self.buff列表().length; i++) {
                //需要比较
                var element = self.buff列表()[i];
                if (element.属性() == buff类别 && element.isActive()) {
                    sum += Number(element.数值());
                }
            }

            for (let j = 0; j < self.内置buff列表.length; j++) {
                var element = self.内置buff列表[j].buff;
                if (element.属性 == buff类别 && element.isActive()) {
                    sum += Number(element.数值);
                }
            }
            return sum;
        }


        // 伤害期望能放在前面吗?
        self.伤害期望 = ko.computed(function () {
            return (Number(self.buff暴击率()) / 100 * (1 + Number(self.buff暴击伤害()) / 100) + (1 - Number(self.buff暴击率()) / 100)) * self.buff攻击力() * (1 + Number(self.buff元素伤害()) / 100);
        });
        self.元素精通提升 = ko.computed(function () {
            var value = (6.665 - 9340 / (Number(self.buff元素精通()) + 1401)) / 2.39
            return value
        });
        self.高倍期望 = ko.computed(function () {
            var value = self.伤害期望() * 2 * (1 + self.元素精通提升());
            return value;
        });
        self.低倍期望 = ko.computed(function () {
            var value = self.伤害期望() * 1.5 * (1 + self.元素精通提升());
            return value;
        });

        self.显示元素反应 = ko.computed(function () {
            return self.输出方式() != self.输出方式列表[4]; // 不属于直伤
        });
        self.显示低倍期望 = ko.computed(function () {
            return self.输出方式() == self.输出方式列表[0] || self.输出方式() == self.输出方式列表[3];
        });
        self.显示高倍期望 = ko.computed(function () {
            return self.输出方式() == self.输出方式列表[1] || self.输出方式() == self.输出方式列表[2];
        });


        // 伤害提升预测
        function 伤害预测(属性) {
            var me = this;
            me.属性 = 属性;
            me.等价数值 = 属性.等价数值;
            var value;
            me.伤害提升 = ko.computed(function () {
                // ((self.buff暴击率()/100 * (1 + self.buff暴击伤害()/100)) + (1 - self.buff暴击率()/100)) * (self.buff攻击力()) * (1 + self.buff元素伤害()/100)
                switch (属性) {
                    case self.属性类型列表[0]: value = ((self.buff暴击率() / 100 * (1 + self.buff暴击伤害() / 100)) + (1 - self.buff暴击率() / 100)) * (self.buff攻击力() + self.基础攻击() * me.等价数值) * (1 + self.buff元素伤害() / 100);
                        break;
                    case self.属性类型列表[1]: value = ((self.buff暴击率() / 100 * (1 + self.buff暴击伤害() / 100)) + (1 - self.buff暴击率() / 100)) * (self.buff攻击力() + me.等价数值) * (1 + self.buff元素伤害() / 100);
                        break;
                    case self.属性类型列表[2]: value = (((me.等价数值 + self.buff暴击率() / 100) * (1 + self.buff暴击伤害() / 100)) + (1 - (me.等价数值 + self.buff暴击率() / 100))) * (self.buff攻击力()) * (1 + self.buff元素伤害() / 100);
                        break;
                    case self.属性类型列表[3]: value = ((self.buff暴击率() / 100 * (1 + self.buff暴击伤害() / 100 + me.等价数值)) + (1 - self.buff暴击率() / 100)) * (self.buff攻击力()) * (1 + self.buff元素伤害() / 100);
                        break;

                    case self.属性类型列表[4]: value = ((self.buff暴击率() / 100 * (1 + self.buff暴击伤害() / 100)) + (1 - self.buff暴击率() / 100)) * (self.buff攻击力()) * (1 + self.buff元素伤害() / 100 + me.等价数值);
                        break;
                    case self.属性类型列表[5]: value = self.伤害期望();
                        break;
                    default: value = 0;
                        break;
                }
                return value / self.伤害期望() - 1;
            });
            me.反应伤害提升 = ko.computed(function () {
                return 属性 == self.属性类型列表[5] ? (self.伤害期望() * 2 * (1 + (6.665 - 9340 / (self.buff元素精通() + 1401 + me.等价数值)) / 2.3965)) / self.高倍期望() - 1 : value / self.伤害期望() - 1;
            });
        }
        self.伤害预测表 = [
            new 伤害预测(self.属性类型列表[0]),
            new 伤害预测(self.属性类型列表[1]),
            new 伤害预测(self.属性类型列表[2]),
            new 伤害预测(self.属性类型列表[3]),
            new 伤害预测(self.属性类型列表[4]),
            new 伤害预测(self.属性类型列表[5]),
        ]
        self.实际伤害 = ko.computed(function () {
            // 注意：string的数字，如果是加法，会直接字符相加，而乘法的时候会自动转为number，因此对于observable应该都转为数字，避免出现问题。（computed方法不会出现这种问题）
            return self.buff攻击力() * (1 + self.buff元素伤害() / 100) * (Number(self.角色等级()) + 100) / (Number(self.角色等级()) + Number(self.怪物等级()) + 200) * 抗性乘区(Number(self.怪物抗性() / 100) - Number(self.减抗效果() / 100)) * Number(self.技能倍率()) / 100;
        });
        self.实际暴击伤害 = ko.computed(function () {
            return self.实际伤害() * (1 + self.buff暴击伤害() / 100);
        })
        self.实际低倍反应伤害 = ko.computed(function () {
            return self.实际伤害() * 1.5 * (1 + self.元素精通提升());
        })
        self.实际低倍暴击反应伤害 = ko.computed(function () {
            return self.实际暴击伤害() * 1.5 * (1 + self.元素精通提升());
        })
        self.实际高倍反应伤害 = ko.computed(function () {
            return self.实际伤害() * 2 * (1 + self.元素精通提升());
        })
        self.实际高倍暴击反应伤害 = ko.computed(function () {
            return self.实际暴击伤害() * 2 * (1 + self.元素精通提升());
        })

    }

    function ToPercentage(value, 百分比 = true) {
        return 百分比 ? (value * 100).toFixed(1) + '%' : value;
    }
    function ToFormatted(value) {
        return value.toFixed(0);
    }
    ko.applyBindings(new ViewModel());
    setTimeout(function () {
        $('[data-toggle="tooltip"]').tooltip()
    },2000)